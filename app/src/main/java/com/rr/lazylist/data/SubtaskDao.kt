package com.rr.lazylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {
    @Query("SELECT * FROM subtasks WHERE parentTaskId = :parentId ORDER BY sortOrder ASC")
    fun getSubtasksForTask(parentId: Long): Flow<List<SubtaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubtask(subtask: SubtaskEntity): Long

    @Update
    suspend fun updateSubtask(subtask: SubtaskEntity)

    @Delete
    suspend fun deleteSubtask(subtask: SubtaskEntity)

    @Query("DELETE FROM subtasks WHERE parentTaskId = :parentId")
    suspend fun deleteAllSubtasksForTask(parentId: Long)

    @Query("UPDATE subtasks SET isCompleted = 1 WHERE parentTaskId = :parentId")
    suspend fun markAllComplete(parentId: Long)

    @Query("SELECT COUNT(*) FROM subtasks WHERE parentTaskId = :parentId AND isCompleted = 0")
    suspend fun getIncompleteCount(parentId: Long): Int
}

