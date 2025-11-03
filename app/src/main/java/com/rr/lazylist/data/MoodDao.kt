package com.rr.lazylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC")
    fun getAllMoods(): Flow<List<MoodEntryEntity>>

    @Query("SELECT * FROM mood_entries WHERE timestamp >= :startTime ORDER BY timestamp DESC")
    fun getMoodsSince(startTime: Long): Flow<List<MoodEntryEntity>>

    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestMood(): MoodEntryEntity?

    @Query("SELECT AVG(energyLevel) FROM mood_entries WHERE timestamp >= :startTime")
    suspend fun getAverageEnergyLevel(startTime: Long): Float?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: MoodEntryEntity): Long

    @Query("DELETE FROM mood_entries WHERE timestamp < :timestamp")
    suspend fun deleteOldMoods(timestamp: Long)
}

