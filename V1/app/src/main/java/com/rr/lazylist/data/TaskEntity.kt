package com.rr.lazylist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val completedAt: Long? = null,
    val emotion: TaskEmotion? = null,
    val isAalsiMode: Boolean = false
)

enum class TaskPriority {
    LOW, MEDIUM, HIGH, URGENT
}

enum class TaskEmotion {
    HAPPY, TIRED, FRUSTRATED, EXCITED, NEUTRAL
}

