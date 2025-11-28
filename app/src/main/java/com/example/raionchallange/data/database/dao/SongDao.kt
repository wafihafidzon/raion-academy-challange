package com.example.raionchallange.data.database.dao

import androidx.room.*
import com.example.raionchallange.data.database.entities.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    
    @Query("SELECT * FROM songs WHERE moodId = :moodId ORDER BY createdAt ASC")
    fun getSongsByMoodId(moodId: Int): Flow<List<SongEntity>>
    
    @Query("SELECT * FROM songs ORDER BY createdAt DESC")
    fun getAllSongs(): Flow<List<SongEntity>>
    
    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun getSongById(id: Int): SongEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)
    
    @Update
    suspend fun updateSong(song: SongEntity)
    
    @Delete
    suspend fun deleteSong(song: SongEntity)
    
    @Query("DELETE FROM songs WHERE id = :id")
    suspend fun deleteSongById(id: Int)
    
    @Query("DELETE FROM songs WHERE moodId = :moodId")
    suspend fun deleteSongsByMoodId(moodId: Int)
    
    @Query("SELECT COUNT(*) FROM songs WHERE moodId = :moodId")
    suspend fun getSongCountByMood(moodId: Int): Int
}