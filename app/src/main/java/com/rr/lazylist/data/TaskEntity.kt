package com.rr.lazylist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val category: TaskCategory = TaskCategory.PERSONAL,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val completedAt: Long? = null,
    val emotion: TaskEmotion? = null,
    val isAalsiMode: Boolean = false,
    val isRecurring: Boolean = false,
    val recurringPattern: RecurringPattern? = null,
    val parentTaskId: Long? = null, // For subtasks
    val voiceNotePath: String? = null,
    val colorTag: String? = null,
    val streak: Int = 0,
    val lastStreakUpdate: Long? = null,
    val notificationId: Int? = null,
    val sortOrder: Int = 0
)

@Entity(tableName = "subtasks")
data class SubtaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val parentTaskId: Long,
    val title: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val sortOrder: Int = 0
)

@Entity(tableName = "task_history")
data class TaskHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val taskId: Long,
    val action: TaskAction,
    val timestamp: Long = System.currentTimeMillis(),
    val notes: String? = null
)

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val achievementType: AchievementType,
    val title: String,
    val description: String,
    val emoji: String,
    val unlockedAt: Long? = null,
    val progress: Int = 0
)

@Entity(tableName = "mood_entries")
data class MoodEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mood: MoodType,
    val timestamp: Long = System.currentTimeMillis(),
    val notes: String? = null,
    val energyLevel: Int = 5 // 1-10
)

enum class TaskPriority {
    LOW, MEDIUM, HIGH, URGENT
}

enum class TaskEmotion {
    HAPPY, TIRED, FRUSTRATED, EXCITED, NEUTRAL, STRESSED, HUNGRY, BORED
}

enum class TaskCategory {
    WORK, PERSONAL, HEALTH, FUN, SHOPPING, LEARNING, FAMILY, URGENT
}

enum class RecurringPattern {
    DAILY, WEEKLY, MONTHLY, WEEKDAYS, WEEKEND, CUSTOM
}

enum class TaskAction {
    CREATED, UPDATED, COMPLETED, DELETED, ARCHIVED, UNARCHIVED
}

enum class AchievementType {
    STREAK_WARRIOR, LAZY_MASTER, PERFECTIONIST, EARLY_BIRD, NIGHT_OWL,
    SHOPPING_PRO, HEALTH_CHAMPION, WORK_AHOLIC, FIRST_TASK, MEGA_STREAK,
    AALSI_LEGEND, VOICE_LOVER, GESTURE_MASTER
}

enum class MoodType {
    HAPPY, SAD, ANGRY, STRESSED, CALM, EXCITED, TIRED, ENERGETIC, NEUTRAL
}

