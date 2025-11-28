package com.example.raionchallange.data.model

import androidx.compose.ui.graphics.Color
import com.example.raionchallange.ui.theme.*

enum class MoodType(
    val displayName: String,
    val description: String,
    val gradientStart: Color,
    val gradientEnd: Color,
    val emoji: String
) {
    HAPPY(
        displayName = "Senang",
        description = "Rasakan energi positif dengan lagu-lagu ceria",
        gradientStart = HappyGradientStart,
        gradientEnd = HappyGradientEnd,
        emoji = "😊"
    ),
    SAD(
        displayName = "Sedih", 
        description = "Temukan ketenangan dengan melodi yang menenangkan",
        gradientStart = SadGradientStart,
        gradientEnd = SadGradientEnd,
        emoji = "😢"
    ),
}