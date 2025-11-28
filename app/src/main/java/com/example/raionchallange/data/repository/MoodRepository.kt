package com.example.raionchallange.data.repository

import com.example.raionchallange.data.database.dao.MoodDao
import com.example.raionchallange.data.database.entities.MoodEntity
import com.example.raionchallange.data.model.Mood
import com.example.raionchallange.data.utils.GradientGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoodRepository(
    private val moodDao: MoodDao
) {

    // Get all moods as Flow for reactive UI
    fun getAllMoods(): Flow<List<Mood>> {
        return moodDao.getAllMoods().map { entities ->
            entities.map { entity -> entity.toDomainModel() }
        }
    }

    // Get mood by ID
    suspend fun getMoodById(id: Int): Mood? {
        return moodDao.getMoodById(id)?.toDomainModel()
    }

    // Insert new mood
    suspend fun insertMood(mood: Mood): Long {
        return moodDao.insertMood(mood.toEntity())
    }

    // Insert multiple moods
    suspend fun insertMoods(moods: List<Mood>) {
        moodDao.insertMoods(moods.map { it.toEntity() })
    }

    // Update mood
    suspend fun updateMood(mood: Mood) {
        moodDao.updateMood(mood.toEntity())
    }

    // Delete mood
    suspend fun deleteMood(mood: Mood) {
        moodDao.deleteMood(mood.toEntity())
    }

    // Delete mood by ID
    suspend fun deleteMoodById(id: Int) {
        moodDao.deleteMoodById(id)
    }

    // Check if database is empty (for initial seeding)
    suspend fun isDatabaseEmpty(): Boolean {
        return moodDao.getMoodCount() == 0
    }

    // Seed default moods with random gradients
    suspend fun seedDefaultMoods() {
        val defaultMoods = listOf(
            Mood(
                name = "Senang",
                description = "Rasakan energi positif dengan lagu-lagu ceria",
                gradientStartColor = "#FF6B6B",
                gradientEndColor = "#FFE66D",
                iconName = "😊"
            ),
            Mood(
                name = "Sedih",
                description = "Temukan ketenangan dengan melodi yang menenangkan",
                gradientStartColor = "#667EEA",
                gradientEndColor = "#764BA2",
                iconName = "😢"
            ),
        )
        
        insertMoods(defaultMoods)
    }
    
    // Generate new mood with random gradient
    suspend fun createMoodWithRandomGradient(
        name: String,
        description: String,
        iconName: String
    ): Long {
        val gradient = GradientGenerator.getGradientForMoodType(name)
        val mood = Mood(
            name = name,
            description = description,
            gradientStartColor = gradient.first,
            gradientEndColor = gradient.second,
            iconName = iconName
        )
        return insertMood(mood)
    }
}

// Extension functions for mapping between domain and data layer
private fun MoodEntity.toDomainModel(): Mood {
    return Mood(
        id = id,
        name = name,
        description = description,
        gradientStartColor = gradientStartColor,
        gradientEndColor = gradientEndColor,
        iconName = iconName,
        createdAt = createdAt
    )
}

private fun Mood.toEntity(): MoodEntity {
    return MoodEntity(
        id = id,
        name = name,
        description = description,
        gradientStartColor = gradientStartColor,
        gradientEndColor = gradientEndColor,
        iconName = iconName,
        createdAt = createdAt
    )
}