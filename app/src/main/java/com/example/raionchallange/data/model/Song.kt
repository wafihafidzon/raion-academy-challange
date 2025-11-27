package com.example.raionchallange.data.model

data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: String, // Format: "3:45"
    val mood: MoodType
)