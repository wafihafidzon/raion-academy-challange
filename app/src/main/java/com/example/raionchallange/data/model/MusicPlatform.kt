package com.example.raionchallange.data.model

enum class MusicPlatform(val displayName: String, val packageName: String) {
    SPOTIFY("Spotify", "com.spotify.music"),
    APPLE_MUSIC("Apple Music", "com.apple.android.music"),
    YOUTUBE_MUSIC("YouTube Music", "com.google.android.apps.youtube.music"),
    DEEZER("Deezer", "deezer.android.app"),
    SOUNDCLOUD("SoundCloud", "com.soundcloud.android"),
    GENERIC("Other", "")
}