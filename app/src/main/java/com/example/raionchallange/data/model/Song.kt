package com.example.raionchallange.data.model

data class Song(
    val id: Int = 0,
    val title: String,
    val artist: String,
    val description: String = "", // Song description
    val streamingUrl: String, // Spotify, Apple Music, YouTube Music, etc
    val platform: MusicPlatform = MusicPlatform.SPOTIFY,
    val moodId: Int
)