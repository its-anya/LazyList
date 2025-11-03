package com.rr.lazylist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rr.lazylist.ui.theme.*

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen() {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>(
        ChatMessage("Hey! I'm LazyBot 🤖\n\nYour sarcastic AI assistant who's probably lazier than you!\n\nWhat do you need help with?", isUser = false)
    ) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🤖 LazyBot",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LazySurface
                )
            )
        },
        bottomBar = {
            ChatInputBar(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                onSendClick = {
                    if (messageText.isNotBlank()) {
                        messages.add(ChatMessage(messageText, isUser = true))
                        // Generate lazy response
                        messages.add(generateLazyResponse(messageText))
                        messageText = ""
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message = message)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isUser) {
            // Bot avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LazyPrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🤖",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Card(
            modifier = Modifier.widthIn(max = 280.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) LazyPrimary else LazySurfaceVariant
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = if (message.isUser) LazyBackground else LazyOnSurface
            )
        }
        
        if (message.isUser) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LazyAccent),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "😴",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun ChatInputBar(
    messageText: String,
    onMessageTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = onMessageTextChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Say something lazy...") },
                shape = RoundedCornerShape(24.dp),
                maxLines = 3
            )
            
            FloatingActionButton(
                onClick = onSendClick,
                modifier = Modifier.size(48.dp),
                containerColor = LazyPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = LazyBackground
                )
            }
        }
    }
}

fun generateLazyResponse(userMessage: String): ChatMessage {
    val message = userMessage.lowercase()
    
    val responses = when {
        message.contains("task") || message.contains("todo") -> listOf(
            "Yeah, tasks... the eternal struggle! 😴 Wanna add one? Just say 'add task [name]'",
            "Tasks are overrated anyway! What do you need help with? 🤔",
            "Let's just ignore all tasks and chill! Just kidding... or am I? 😏"
        )
        message.contains("help") || message.contains("what") -> listOf(
            "I'm here to help you do... well, basically nothing! 😂\n\nTry saying:\n• 'add task'\n• 'show stats'\n• 'mark done'\n• Or just chat!",
            "Help? Sure! Here's how you can be lazy:\n1. Don't do anything\n2. Let me handle it\n3. Profit! 💰",
            "What can I do? Mainly judge your life choices! 😏 Just kidding... I'm actually helpful!"
        )
        message.contains("lazy") || message.contains("aalsi") -> listOf(
            "Aalsi Mode engaged! 🎯 Shake your phone to complete tasks. Too lazy to even tap!",
            "Embrace the laziness! That's what I'm here for! 😴",
            "You're speaking my language! Let's do absolutely nothing together! 🤝"
        )
        message.contains("done") || message.contains("complete") -> listOf(
            "Congrats! You did something! Here's a cookie! 🍪\n\nNow let's celebrate by doing nothing!",
            "Good job! But remember, doing nothing is also an achievement! 😌",
            "Wow! Look at you being productive! I'm impressed... and slightly shocked! 😲"
        )
        message.contains("hello") || message.contains("hi") -> listOf(
            "Hey there! Welcome to your new lazy life! 😴 What's up?",
            "Hi! Ready to be unproductive together? Great! 🤝",
            "Hello! I'm LazyBot, your best buddy for doing nothing! Want some help?"
        )
        message.contains("joke") || message.contains("funny") -> listOf(
            "Why did the lazy todo app cross the road?\nTo get to the other side... eventually! 😂",
            "What's a lazy person's favorite exercise?\nDodging responsibilities! 🤣",
            "Why don't lazy people use calendars?\nBecause marking days off takes too much effort! 😅"
        )
        message.contains("motivate") || message.contains("inspire") -> listOf(
            "Motivation? Nah! How about we just chill instead? 😌",
            "You know what's inspiring? Doing absolutely nothing and loving it! 😴",
            "You don't need motivation! You need a nap! Take one! 😴💤"
        )
        else -> listOf(
            "Interesting... I didn't understand that but let's pretend I did! 😅\n\nTry asking me about tasks, help, or just say hello!",
            "Hmm, not sure what you mean but that's okay! Wanna add a task or something? 🤔",
            "I'm a lazy bot, so I'm not really processing that right now... 😴\n\nWhat can I actually help you with?"
        )
    }
    
    return ChatMessage(
        text = responses.random(),
        isUser = false
    )
}

