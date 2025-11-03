package com.rr.lazylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements")
    fun getAllAchievements(): Flow<List<AchievementEntity>>

    @Query("SELECT * FROM achievements WHERE achievementType = :type")
    suspend fun getAchievementByType(type: AchievementType): AchievementEntity?

    @Query("SELECT * FROM achievements WHERE unlockedAt IS NOT NULL")
    fun getUnlockedAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: AchievementEntity): Long

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)

    @Query("UPDATE achievements SET unlockedAt = :timestamp, progress = :progress WHERE achievementType = :type")
    suspend fun unlockAchievement(type: AchievementType, timestamp: Long, progress: Int)
}

