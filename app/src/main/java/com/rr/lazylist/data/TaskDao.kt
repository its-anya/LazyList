package com.rr.lazylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY createdAt DESC")
    fun getActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY completedAt DESC")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE isCompleted = 1")
    suspend fun deleteAllCompleted()

    @Query("UPDATE tasks SET isCompleted = 1, completedAt = :completedAt WHERE id = :id")
    suspend fun markAsCompleted(id: Long, completedAt: Long = System.currentTimeMillis())

    @Query("UPDATE tasks SET isCompleted = 0, completedAt = NULL WHERE id = :id")
    suspend fun markAsIncomplete(id: Long)

    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 0")
    suspend fun getActiveTaskCount(): Int

    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 1 AND completedAt >= :startOfDay")
    suspend fun getCompletedTodayCount(startOfDay: Long): Int
}

