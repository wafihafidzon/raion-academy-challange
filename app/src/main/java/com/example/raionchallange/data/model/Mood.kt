package com.example.raionchallange.data.model

import java.util.Date

data class Mood(
    val id: Int = 0,
    val name: String,
    val description: String,
    val gradientStartColor: String,
    val gradientEndColor: String,
    val iconName: String,
    val createdAt: Long = Date().time
)