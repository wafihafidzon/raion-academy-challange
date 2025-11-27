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
    ENERGETIC(
        displayName = "Berenergi",
        description = "Musik penuh semangat untuk aktivitas intens",
        gradientStart = EnergeticGradientStart,
        gradientEnd = EnergeticGradientEnd,
        emoji = "⚡"
    ),
    CHILL(
        displayName = "Santai",
        description = "Musik rileks untuk momen tenang",
        gradientStart = ChillGradientStart,
        gradientEnd = ChillGradientEnd,
        emoji = "😌"
    ),
    ROMANTIC(
        displayName = "Romantis",
        description = "Lagu cinta untuk momen berdua",
        gradientStart = RomanticGradientStart,
        gradientEnd = RomanticGradientEnd,
        emoji = "💕"
    ),
    FOCUS(
        displayName = "Fokus",
        description = "Musik instrumental untuk konsentrasi",
        gradientStart = FocusGradientStart,
        gradientEnd = FocusGradientEnd,
        emoji = "🧠"
    )
}