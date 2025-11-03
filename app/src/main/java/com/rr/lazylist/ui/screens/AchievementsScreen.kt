package com.rr.lazylist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rr.lazylist.ui.theme.*

data class Achievement(
    val title: String,
    val description: String,
    val emoji: String,
    val isUnlocked: Boolean,
    val progress: Int = 0,
    val maxProgress: Int = 100
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen() {
    val achievements = remember {
        listOf(
            Achievement("First Task", "Complete your first task", "🎉", true, 100, 100),
            Achievement("Streak Warrior", "Complete 7 tasks in a row", "🔥", true, 100, 7),
            Achievement("Lazy Master", "Use Aalsi Mode 10 times", "😴", false, 5, 10),
            Achievement("Perfectionist", "Complete 100 tasks", "⭐", false, 32, 100),
            Achievement("Voice Lover", "Add 50 tasks using voice", "🎤", false, 8, 50),
            Achievement("Mega Streak", "30 day streak!", "🚀", false, 7, 30),
            Achievement("Early Bird", "Complete 10 morning tasks", "🌅", false, 3, 10),
            Achievement("Night Owl", "Complete 10 evening tasks", "🦉", true, 100, 10),
            Achievement("Shopping Pro", "Complete 50 shopping tasks", "🛒", false, 12, 50),
            Achievement("Aalsi Legend", "Master the art of laziness", "👑", false, 0, 100)
        )
    }
    
    val unlockedCount = achievements.count { it.isUnlocked }
    val totalCount = achievements.size
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "🏆 Achievements",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Stats banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LazyPrimary.copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$unlockedCount",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = LazyPrimary
                        )
                        Text(
                            text = "Unlocked",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$totalCount",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = LazySecondary
                        )
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${(unlockedCount * 100 / totalCount)}%",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = LazyAccent
                        )
                        Text(
                            text = "Progress",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            
            // Grid of achievements
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(achievements) { achievement ->
                    AchievementCard(achievement)
                }
            }
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.isUnlocked) 
                LazyPrimary.copy(alpha = 0.1f) 
            else 
                LazySurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (achievement.isUnlocked) achievement.emoji else "🔒",
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = achievement.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = achievement.description,
                style = MaterialTheme.typography.bodySmall,
                color = LazyOnSurface.copy(alpha = 0.7f)
            )
            
            if (!achievement.isUnlocked) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${achievement.progress}/${achievement.maxProgress}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = LazyAccent
                )
            }
        }
    }
}

