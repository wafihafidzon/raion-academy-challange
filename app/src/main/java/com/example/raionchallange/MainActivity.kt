package com.example.raionchallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.ui.screens.mood.MoodSelectionScreen
import com.example.raionchallange.ui.screens.musiclist.MusicListScreen
import com.example.raionchallange.ui.theme.RaionChallangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaionChallangeTheme {
                MoodTunesApp()
            }
        }
    }
}

@Composable
fun MoodTunesApp() {
    var selectedMood by remember { mutableStateOf<MoodType?>(null) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (selectedMood == null) {
            MoodSelectionScreen(
                onMoodSelected = { mood ->
                    selectedMood = mood
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            MusicListScreen(
                mood = selectedMood!!,
                onBackClick = {
                    selectedMood = null
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}