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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raionchallange.data.database.MoodDatabase
import com.example.raionchallange.data.model.Mood
import com.example.raionchallange.data.repository.MoodRepository
import com.example.raionchallange.ui.screens.mood.MoodSelectionScreen
import com.example.raionchallange.ui.screens.musiclist.MusicListScreen
import com.example.raionchallange.ui.theme.RaionChallangeTheme
import com.example.raionchallange.ui.viewmodel.MoodViewModel
import com.example.raionchallange.ui.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = MoodDatabase.getDatabase(this)
        val moodRepository = MoodRepository(db.moodDao())
        val factory = ViewModelFactory(moodRepository)

        enableEdgeToEdge()
        setContent {
            RaionChallangeTheme {
                MoodTunesApp(factory = factory)
            }
        }
    }
}

@Composable
fun MoodTunesApp(factory: ViewModelFactory) {
    var selectedMood by remember { mutableStateOf<Mood?>(null) }
    var selectedSong by remember { mutableStateOf<com.example.raionchallange.data.model.Song?>(null) }
    val moodViewModel: MoodViewModel = viewModel(factory = factory)

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when {
            selectedSong != null -> {
                com.example.raionchallange.ui.screens.songdetail.SongDetailScreen(
                    song = selectedSong!!,
                    onBackClick = {
                        selectedSong = null
                    }
                )
            }
            selectedMood != null -> {
                MusicListScreen(
                    mood = selectedMood!!,
                    onBackClick = {
                        selectedMood = null
                    },
                    onSongClick = { song ->
                        selectedSong = song
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            else -> {
                MoodSelectionScreen(
                    viewModel = moodViewModel,
                    onMoodSelected = { mood ->
                        selectedMood = mood
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}