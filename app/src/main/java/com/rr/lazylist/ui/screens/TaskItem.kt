package com.rr.lazylist.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.rr.lazylist.data.TaskEntity
import com.rr.lazylist.data.TaskPriority
import com.rr.lazylist.ui.theme.*

@Composable
fun TaskItem(
    task: TaskEntity,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    isAalsiMode: Boolean = false
) {
    AnimatedVisibility(
        visible = true,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(
                containerColor = if (task.isCompleted) LazySurfaceVariant else LazySurface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onCompleteClick() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = if (isAalsiMode) LazySecondary else LazyCompleted,
                        uncheckedColor = LazyOnSurface
                    )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (task.isCompleted) LazyOnSurface.copy(alpha = 0.6f) else LazyOnSurface,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )

                    if (task.description.isNotEmpty() && !task.isCompleted) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = LazyOnSurface.copy(alpha = 0.7f)
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PriorityBadge(priority = task.priority)
                        if (isAalsiMode) {
                            Chip(
                                text = "Aalsi Mode",
                                modifier = Modifier,
                                onClick = {}
                            )
                        }
                        task.emotion?.let {
                            EmotionChip(emotion = it)
                        }
                    }
                }

                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = LazyAccent,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PriorityBadge(priority: TaskPriority) {
    val (color, text) = when (priority) {
        TaskPriority.LOW -> LazySecondary.copy(alpha = 0.3f) to "Low"
        TaskPriority.MEDIUM -> LazyPrimary.copy(alpha = 0.3f) to "Med"
        TaskPriority.HIGH -> LazyAccent.copy(alpha = 0.3f) to "High"
        TaskPriority.URGENT -> LazyAccent to "🔥"
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = if (priority == TaskPriority.URGENT) LazyOnBackground else LazyOnSurface
        )
    }
}

@Composable
fun EmotionChip(emotion: com.rr.lazylist.data.TaskEmotion) {
    val emoji = when (emotion) {
        com.rr.lazylist.data.TaskEmotion.HAPPY -> "😊"
        com.rr.lazylist.data.TaskEmotion.TIRED -> "😴"
        com.rr.lazylist.data.TaskEmotion.FRUSTRATED -> "😤"
        com.rr.lazylist.data.TaskEmotion.EXCITED -> "🤩"
        com.rr.lazylist.data.TaskEmotion.NEUTRAL -> "😐"
        com.rr.lazylist.data.TaskEmotion.STRESSED -> "😰"
        com.rr.lazylist.data.TaskEmotion.HUNGRY -> "🍕"
        com.rr.lazylist.data.TaskEmotion.BORED -> "🥱"
    }

    Text(
        text = emoji,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        color = LazyPrimary.copy(alpha = 0.2f),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = LazyPrimary
        )
    }
}

