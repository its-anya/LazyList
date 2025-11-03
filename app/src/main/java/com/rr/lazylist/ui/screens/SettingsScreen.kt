package com.rr.lazylist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rr.lazylist.ui.theme.*

data class SettingItem(
    val title: String,
    val subtitle: String? = null,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "⚙️ Settings",
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
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                SettingsSectionTitle("Appearance")
            }
            
            items(getAppearanceSettings()) { item ->
                SettingItemRow(item)
            }
            
            item {
                SettingsSectionTitle("Behavior")
            }
            
            items(getBehaviorSettings()) { item ->
                SettingItemRow(item)
            }
            
            item {
                SettingsSectionTitle("Notifications")
            }
            
            items(getNotificationSettings()) { item ->
                SettingItemRow(item)
            }
            
            item {
                SettingsSectionTitle("About")
            }
            
            items(getAboutSettings()) { item ->
                SettingItemRow(item)
            }
        }
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = LazyPrimary
    )
}

@Composable
fun SettingItemRow(item: SettingItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = item.onClick,
        colors = CardDefaults.cardColors(
            containerColor = LazySurface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = LazyPrimary
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                item.subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = LazyOnSurface.copy(alpha = 0.7f)
                    )
                }
            }
            
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = LazyOnSurface.copy(alpha = 0.5f)
            )
        }
    }
}

fun getAppearanceSettings(): List<SettingItem> = listOf(
    SettingItem(
        title = "Theme",
        subtitle = "Dark (Default)",
        icon = Icons.Default.Brush,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Mascot",
        subtitle = "Change LazyBot appearance",
        icon = Icons.Default.Face,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Color Accent",
        subtitle = "Purple (Default)",
        icon = Icons.Default.Favorite,
        onClick = { /* TODO */ }
    )
)

fun getBehaviorSettings(): List<SettingItem> = listOf(
    SettingItem(
        title = "Aalsi Mode",
        subtitle = "Enable shake-to-complete",
        icon = Icons.Default.Phone,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Shake Sensitivity",
        subtitle = "Medium",
        icon = Icons.Default.Refresh,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Voice Recognition",
        subtitle = "Google Speech API",
        icon = Icons.Default.Mic,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Auto-suggestions",
        subtitle = "Every 5 minutes",
        icon = Icons.Default.Lightbulb,
        onClick = { /* TODO */ }
    )
)

fun getNotificationSettings(): List<SettingItem> = listOf(
    SettingItem(
        title = "Reminders",
        subtitle = "Enabled",
        icon = Icons.Default.Notifications,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Daily Summary",
        subtitle = "9 PM",
        icon = Icons.Default.List,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Streak Notifications",
        subtitle = "Enabled",
        icon = Icons.Default.LocalFireDepartment,
        onClick = { /* TODO */ }
    )
)

fun getAboutSettings(): List<SettingItem> = listOf(
    SettingItem(
        title = "Version",
        subtitle = "1.0.0",
        icon = Icons.Default.Info,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Privacy Policy",
        subtitle = "View our privacy practices",
        icon = Icons.Default.Lock,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Export Data",
        subtitle = "Backup your tasks",
        icon = Icons.Default.CloudUpload,
        onClick = { /* TODO */ }
    ),
    SettingItem(
        title = "Rate App",
        subtitle = "Help us improve",
        icon = Icons.Default.Star,
        onClick = { /* TODO */ }
    )
)

