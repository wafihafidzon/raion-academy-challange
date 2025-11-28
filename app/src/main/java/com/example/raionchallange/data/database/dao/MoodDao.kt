package com.example.raionchallange.data.database.dao

import androidx.room.*
import com.example.raionchallange.data.database.entities.MoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    
    @Query("SELECT * FROM moods ORDER BY createdAt ASC")
    fun getAllMoods(): Flow<List<MoodEntity>>
    
    @Query("SELECT * FROM moods WHERE id = :id")
    suspend fun getMoodById(id: Int): MoodEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: MoodEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoods(moods: List<MoodEntity>)
    
    @Update
    suspend fun updateMood(mood: MoodEntity)
    
    @Delete
    suspend fun deleteMood(mood: MoodEntity)
    
    @Query("DELETE FROM moods WHERE id = :id")
    suspend fun deleteMoodById(id: Int)
    
    @Query("SELECT COUNT(*) FROM moods")
    suspend fun getMoodCount(): Int
}