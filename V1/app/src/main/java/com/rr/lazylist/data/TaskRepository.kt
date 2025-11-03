package com.rr.lazylist.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class TaskRepository(private val taskDao: TaskDao) {
    
    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()
    val activeTasks: Flow<List<TaskEntity>> = taskDao.getActiveTasks()
    val completedTasks: Flow<List<TaskEntity>> = taskDao.getCompletedTasks()

    suspend fun getTaskById(id: Long): TaskEntity? {
        return taskDao.getTaskById(id)
    }

    suspend fun insertTask(task: TaskEntity): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllCompleted() {
        taskDao.deleteAllCompleted()
    }

    suspend fun markAsCompleted(id: Long) {
        taskDao.markAsCompleted(id)
    }

    suspend fun markAsIncomplete(id: Long) {
        taskDao.markAsIncomplete(id)
    }

    suspend fun getActiveTaskCount(): Int {
        return taskDao.getActiveTaskCount()
    }

    suspend fun getCompletedTodayCount(): Int {
        val startOfDay = Date().apply {
            hours = 0
            minutes = 0
            seconds = 0
        }.time
        return taskDao.getCompletedTodayCount(startOfDay)
    }

    suspend fun createTaskFromVoice(
        title: String,
        emotion: TaskEmotion? = null,
        isAalsiMode: Boolean = false
    ): TaskEntity {
        val priority = when (emotion) {
            TaskEmotion.FRUSTRATED, TaskEmotion.EXCITED -> TaskPriority.HIGH
            TaskEmotion.TIRED -> TaskPriority.LOW
            else -> TaskPriority.MEDIUM
        }

        return TaskEntity(
            title = title,
            emotion = emotion,
            priority = priority,
            isAalsiMode = isAalsiMode
        )
    }
}

