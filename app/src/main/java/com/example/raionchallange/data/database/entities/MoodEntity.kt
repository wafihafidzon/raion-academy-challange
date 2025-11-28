package com.example.raionchallange.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val gradientStartColor: String, // Hex color string
    val gradientEndColor: String,   // Hex color string
    val iconName: String,           // Emoji or icon reference
    val createdAt: Long = System.currentTimeMillis()
)