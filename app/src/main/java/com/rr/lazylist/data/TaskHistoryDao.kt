package com.rr.lazylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskHistoryDao {
    @Query("SELECT * FROM task_history WHERE taskId = :taskId ORDER BY timestamp DESC")
    fun getHistoryForTask(taskId: Long): Flow<List<TaskHistoryEntity>>

    @Query("SELECT * FROM task_history ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentHistory(limit: Int = 50): Flow<List<TaskHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: TaskHistoryEntity): Long

    @Query("DELETE FROM task_history WHERE timestamp < :timestamp")
    suspend fun deleteOldHistory(timestamp: Long)
}

