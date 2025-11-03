package com.rr.lazylist.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rr.lazylist.data.TaskDatabase
import com.rr.lazylist.data.TaskRepository
import com.rr.lazylist.ui.services.ShakeDetector
import com.rr.lazylist.ui.services.VoiceRecognitionService
import com.rr.lazylist.ui.theme.*
import com.rr.lazylist.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {
    val context = LocalContext.current
    val database = TaskDatabase.getDatabase(context)
    val repository = TaskRepository(database.taskDao())
    val viewModel: TaskViewModel = viewModel(
        factory = TaskViewModel.provideFactory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()
    val voiceService = remember { VoiceRecognitionService(context) }
    val shakeDetector = remember {
        val sensorManager = context.getSystemService(android.content.Context.SENSOR_SERVICE) as android.hardware.SensorManager
        ShakeDetector(sensorManager)
    }

    val voiceResult by voiceService.voiceResult.collectAsState()
    val isListening by voiceService.isListening.collectAsState()
    val shakeDetected by shakeDetector.shakeDetected.collectAsState()

    var showSuggestionDialog by remember { mutableStateOf(false) }

    LaunchedEffect(shakeDetected) {
        if (shakeDetected && uiState.isAalsiModeEnabled && uiState.activeTasks.isNotEmpty()) {
            // Mark first active task as complete when shake detected in Aalsi mode
            uiState.activeTasks.firstOrNull()?.let {
                viewModel.markTaskComplete(it.id)
                showSuggestionDialog = true
            }
        }
    }

    LaunchedEffect(voiceResult) {
        voiceResult?.let { text ->
            viewModel.addTaskFromVoice(text)
            voiceService.clearResult()
        }
    }

    DisposableEffect(Unit) {
        shakeDetector.start()
        onDispose {
            shakeDetector.stop()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            voiceService.destroy()
        }
    }

    LaunchedEffect(uiState.lazySuggestion) {
        uiState.lazySuggestion?.let {
            showSuggestionDialog = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "😴 LazyList",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        if (uiState.isAalsiModeEnabled) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Chip(text = "Aalsi Mode 🔥")
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleAalsiMode() }) {
                        Icon(
                            imageVector = if (uiState.isAalsiModeEnabled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Toggle Aalsi Mode",
                            tint = if (uiState.isAalsiModeEnabled) LazyAccent else LazyOnSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LazySurface
                )
            )
        },
        floatingActionButton = {
            FloatingVoiceButton(
                isListening = isListening,
                onClick = { voiceService.startListening() },
                onLongPress = { voiceService.startListening() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            voiceService.startListening()
                        }
                    )
                }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (uiState.activeTasks.isEmpty() && uiState.completedTasks.isEmpty()) {
                    item {
                        EmptyTaskView()
                    }
                } else {
                    if (uiState.activeTasks.isNotEmpty()) {
                        item {
                            SectionHeader("Active Tasks")
                        }
                        items(uiState.activeTasks) { task ->
                            TaskItem(
                                task = task,
                                onCompleteClick = { viewModel.markTaskComplete(task.id) },
                                onDeleteClick = { viewModel.deleteTask(task) },
                                isAalsiMode = uiState.isAalsiModeEnabled
                            )
                        }
                    }

                    if (uiState.completedTasks.isNotEmpty()) {
                        item {
                            SectionHeader("Completed")
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = { viewModel.deleteAllCompleted() }
                                ) {
                                    Text(
                                        text = "Clear All",
                                        color = LazyAccent
                                    )
                                }
                            }
                        }
                        items(uiState.completedTasks.take(10)) { task ->
                            TaskItem(
                                task = task,
                                onCompleteClick = { viewModel.markTaskIncomplete(task.id) },
                                onDeleteClick = { viewModel.deleteTask(task) },
                                isAalsiMode = false
                            )
                        }
                    }
                }
            }
        }

        if (showSuggestionDialog) {
            LazySuggestionDialog(
                suggestion = uiState.lazySuggestion ?: "Task completed! 🎉",
                onAccept = {
                    uiState.lazySuggestion?.let {
                        viewModel.acceptLazySuggestion(it)
                    }
                    showSuggestionDialog = false
                },
                onDismiss = {
                    viewModel.dismissLazySuggestion()
                    showSuggestionDialog = false
                }
            )
        }
    }
}

@Composable
fun FloatingVoiceButton(
    isListening: Boolean,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isListening) 1.2f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.scale(scale),
        containerColor = if (isListening) LazyAccent else LazyPrimary
    ) {
        Icon(
            imageVector = if (isListening) Icons.Default.Close else Icons.Default.Add,
            contentDescription = "Voice Input",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun EmptyTaskView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "😴",
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Too lazy for tasks?",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tap & hold anywhere to speak or\ntap the mic to add tasks",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = LazyOnSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        color = LazyPrimary
    )
}

@Composable
fun LazySuggestionDialog(
    suggestion: String,
    onAccept: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "🎯 Lazy Suggestion",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Text(text = suggestion)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Too lazy to think? Let's do it! 🚀",
                    style = MaterialTheme.typography.bodySmall,
                    color = LazyOnSurface.copy(alpha = 0.7f)
                )
            }
        },
        confirmButton = {
            Button(onClick = onAccept) {
                Text("Yes, Add It!")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Nah, Later")
            }
        },
        containerColor = LazySurface
    )
}

