package com.example.raionchallange.data.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlin.random.Random

object GradientGenerator {
    
    // Predefined color palettes for consistent and beautiful gradients
    private val warmPalettes = listOf(
        Pair("#FF6B6B", "#FFE66D"), // Red to Yellow
        Pair("#FF9A9E", "#FECAB7"), // Pink to Peach  
        Pair("#FFB75C", "#ED4264"), // Orange to Pink
        Pair("#FFA726", "#FB8C00"), // Orange variations
        Pair("#FF5722", "#FF9800")  // Deep orange to orange
    )
    
    private val coolPalettes = listOf(
        Pair("#667EEA", "#764BA2"), // Blue to Purple
        Pair("#43CBFF", "#9708CC"), // Cyan to Purple
        Pair("#8FD3F4", "#84FAB0"), // Light Blue to Green
        Pair("#2196F3", "#3F51B5"), // Blue to Indigo
        Pair("#00BCD4", "#009688")  // Cyan to Teal
    )
    
    private val neutralPalettes = listOf(
        Pair("#757F9A", "#D7DDE8"), // Gray gradient
        Pair("#8E9AAF", "#CBC0D3"), // Soft gray
        Pair("#B2BEB5", "#E6E6FA"), // Light gray to lavender
        Pair("#A8A8A8", "#D3D3D3"), // Gray variations
        Pair("#708090", "#C0C0C0")  // Slate to silver
    )
    
    private val vibrantPalettes = listOf(
        Pair("#FF0844", "#FFB199"), // Vibrant red to peach
        Pair("#4FACFE", "#00F2FE"), // Blue to cyan
        Pair("#A8EDEA", "#FED6E3"), // Mint to pink
        Pair("#D299C2", "#FEF9D7"), // Purple to cream
        Pair("#89F7FE", "#66A6FF")  // Light cyan to blue
    )
    
    /**
     * Generate a random gradient from predefined palettes
     * @param category: "warm", "cool", "neutral", "vibrant", or "random"
     */
    fun generateGradient(category: String = "random"): Pair<String, String> {
        val palette = when (category.lowercase()) {
            "warm" -> warmPalettes
            "cool" -> coolPalettes  
            "neutral" -> neutralPalettes
            "vibrant" -> vibrantPalettes
            else -> warmPalettes + coolPalettes + neutralPalettes + vibrantPalettes
        }
        
        return palette.random()
    }
    
    /**
     * Generate completely random gradient using HSV color space
     * for better color harmony
     */
    fun generateRandomHSVGradient(): Pair<String, String> {
        val baseHue = Random.nextFloat() * 360f
        val complementaryHue = (baseHue + Random.nextInt(30, 60)) % 360f
        
        val startColor = Color.hsv(baseHue, 0.7f, 0.9f)
        val endColor = Color.hsv(complementaryHue, 0.7f, 0.9f)
        
        return Pair(
            "#${startColor.toArgb().toUInt().toString(16).takeLast(6).uppercase()}",
            "#${endColor.toArgb().toUInt().toString(16).takeLast(6).uppercase()}"
        )
    }
    
    /**
     * Get gradient for specific mood type (predefined mapping)
     */
    fun getGradientForMoodType(moodName: String): Pair<String, String> {
        return when (moodName.lowercase()) {
            "senang", "happy" -> warmPalettes.random()
            "sedih", "sad" -> coolPalettes.random()
            else -> generateGradient("random")
        }
    }
}