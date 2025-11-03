package com.rr.lazylist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rr.lazylist.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "📊 Your Lazy Stats",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LazySurface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                DailySummaryCard()
            }
            
            item {
                StreakCard(currentStreak = 7, bestStreak = 15)
            }
            
            item {
                CategoryBreakdownCard()
            }
            
            item {
                MoodChartCard()
            }
            
            item {
                LazyInsightCard()
            }
        }
    }
}

@Composable
fun DailySummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LazyPrimary.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "📅 Today's Progress",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LazyPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "Completed", value = "3", emoji = "✅")
                StatItem(label = "Active", value = "7", emoji = "📝")
                StatItem(label = "Progress", value = "30%", emoji = "📈")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, emoji: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = emoji,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = LazyOnSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun StreakCard(currentStreak: Int, bestStreak: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LazyAccent.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "🔥 Streak Stats",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LazyAccent
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "Current", value = "$currentStreak", emoji = "🔥")
                StatItem(label = "Best", value = "$bestStreak", emoji = "🏆")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Keep it up! You're on fire! 🔥",
                style = MaterialTheme.typography.bodyMedium,
                color = LazyOnSurface.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CategoryBreakdownCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LazySecondary.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "📁 Category Breakdown",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LazySecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            CategoryItem("Work", 8, LazyAccent)
            CategoryItem("Health", 5, LazyCompleted)
            CategoryItem("Personal", 12, LazyPrimary)
            CategoryItem("Fun", 3, LazySecondary)
        }
    }
}

@Composable
fun CategoryItem(name: String, count: Int, color: androidx.compose.ui.graphics.Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProgressBar(progress = count / 20f, color = color, modifier = Modifier.width(100.dp))
            Text(
                text = "$count",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProgressBar(
    progress: Float,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(LazyOnSurface.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .background(color)
        )
    }
}

@Composable
fun MoodChartCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LazyPurple.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "😊 Mood Trend",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LazyPurple
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Your mood this week: Overall Happy! 😊",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MoodIndicator("😊", "Happy")
                MoodIndicator("😴", "Tired")
                MoodIndicator("🤩", "Excited")
                MoodIndicator("😐", "Neutral")
            }
        }
    }
}

@Composable
fun MoodIndicator(emoji: String, label: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = emoji,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = LazyOnSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun LazyInsightCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LazySurfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "💭 Lazy Insights",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            InsightText("You complete 30% more tasks when using Aalsi Mode! 🎉")
            Spacer(modifier = Modifier.height(12.dp))
            InsightText("Your most productive time: 10 AM - 12 PM ⏰")
            Spacer(modifier = Modifier.height(12.dp))
            InsightText("You're 50% lazier on weekends. Classic! 😴")
        }
    }
}

@Composable
fun InsightText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "💡",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = LazyOnSurface.copy(alpha = 0.9f)
        )
    }
}

