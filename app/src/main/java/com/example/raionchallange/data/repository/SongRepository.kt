package com.example.raionchallange.data.repository

import com.example.raionchallange.data.database.dao.SongDao
import com.example.raionchallange.data.database.entities.SongEntity
import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.data.model.MusicPlatform
import com.example.raionchallange.data.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SongRepository(
    private val songDao: SongDao
) {

    // Get songs by mood ID
    fun getSongsByMoodId(moodId: Int): Flow<List<Song>> {
        return songDao.getSongsByMoodId(moodId).map { entities ->
            entities.map { entity -> entity.toDomainModel() }
        }
    }

    // Get all songs
    fun getAllSongs(): Flow<List<Song>> {
        return songDao.getAllSongs().map { entities ->
            entities.map { entity -> entity.toDomainModel() }
        }
    }

    // Get song by ID
    suspend fun getSongById(id: Int): Song? {
        return songDao.getSongById(id)?.toDomainModel()
    }

    // Insert new song
    suspend fun insertSong(song: Song): Long {
        return songDao.insertSong(song.toEntity())
    }

    // Insert multiple songs
    suspend fun insertSongs(songs: List<Song>) {
        songDao.insertSongs(songs.map { it.toEntity() })
    }

    // Update song
    suspend fun updateSong(song: Song) {
        songDao.updateSong(song.toEntity())
    }

    // Delete song
    suspend fun deleteSong(song: Song) {
        songDao.deleteSong(song.toEntity())
    }

    // Delete song by ID
    suspend fun deleteSongById(id: Int) {
        songDao.deleteSongById(id)
    }

    // Delete all songs by mood ID
    suspend fun deleteSongsByMoodId(moodId: Int) {
        songDao.deleteSongsByMoodId(moodId)
    }

    // Get song count by mood
    suspend fun getSongCountByMood(moodId: Int): Int {
        return songDao.getSongCountByMood(moodId)
    }

    // Seed sample songs for testing
    suspend fun seedSampleSongs(moodId: Int, moodName: String) {
        val sampleSongs = when (moodName.lowercase()) {
            "senang", "happy" -> listOf(
                Song(
                    title = "Happy",    
                    artist = "Pharrell Williams", 
                    description = "Upbeat song that brings joy and positivity",
                    streamingUrl = "https://open.spotify.com/track/60nZcImufyMA1MKQY3dcCH", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                ),
                Song(
                    title = "Walking on Sunshine", 
                    artist = "Katrina & The Waves", 
                    description = "Classic feel-good anthem",
                    streamingUrl = "https://open.spotify.com/track/05wIrZSwuaVWhcv5FfqeH0", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                ),
                Song(
                    title = "Good as Hell", 
                    artist = "Lizzo", 
                    description = "Empowering and energetic track",
                    streamingUrl = "https://open.spotify.com/track/1BxfuPKGuaTgP7aM0Bbdwr", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                )
            )
            "sedih", "sad" -> listOf(
                Song(
                    title = "Someone Like You", 
                    artist = "Adele", 
                    description = "Emotional ballad about lost love",
                    streamingUrl = "https://open.spotify.com/track/4sPmO7WMQUAf45kwMOtONw", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                ),
                Song(
                    title = "Hurt", 
                    artist = "Johnny Cash", 
                    description = "Haunting cover that touches the soul",
                    streamingUrl = "https://open.spotify.com/track/2B2qnGrAh7RTK0fhSXTp7g", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                ),
                Song(
                    title = "Mad World", 
                    artist = "Gary Jules", 
                    description = "Melancholic interpretation of the classic",
                    streamingUrl = "https://open.spotify.com/track/3JOVTQ5h8HGFnDdp4VT3MP", 
                    platform = MusicPlatform.SPOTIFY,
                    moodId = moodId
                )
            )
            else -> emptyList()
        }

        insertSongs(sampleSongs)
    }
}

// Extension functions for mapping
private fun SongEntity.toDomainModel(): Song {
    return Song(
        id = id,
        title = title,
        artist = artist,
        description = description,
        streamingUrl = streamingUrl,
        platform = MusicPlatform.values().find { it.name == platform } ?: MusicPlatform.GENERIC,
        moodId = moodId
    )
}

private fun Song.toEntity(): SongEntity {
    return SongEntity(
        id = id,
        title = title,
        artist = artist,
        description = description,
        streamingUrl = streamingUrl,
        platform = platform.name,
        moodId = moodId
    )
}
