package com.example.raionchallange.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "songs",
    foreignKeys = [
        ForeignKey(
            entity = MoodEntity::class,
            parentColumns = ["id"],
            childColumns = ["moodId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["moodId"])] // For faster queries
)
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artist: String,
    val description: String = "",
    val streamingUrl: String,
    val platform: String, // Store as string to avoid Room complexity
    val moodId: Int,
    val createdAt: Long = System.currentTimeMillis()
)