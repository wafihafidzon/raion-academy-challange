package com.example.raionchallange.ui.screens.musiclist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.data.model.Song
import com.example.raionchallange.data.repository.MusicRepository
import com.example.raionchallange.ui.theme.BackgroundGradientEnd
import com.example.raionchallange.ui.theme.BackgroundGradientStart
import com.example.raionchallange.ui.theme.GraySubtitle
import com.example.raionchallange.ui.theme.WhiteAlpha90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListScreen(
    mood: MoodType,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val songs = MusicRepository.getSongsByMood(mood)
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundGradientStart,
                        BackgroundGradientEnd
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Musik ${mood.displayName}",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "${songs.size} lagu tersedia",
                            color = GraySubtitle,
                            fontSize = 12.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            // Song List
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(songs) { song ->
                    SongCard(
                        song = song,
                        mood = mood
                    )
                }
            }
        }
    }
}

@Composable
fun SongCard(
    song: Song,
    mood: MoodType,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Song Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = song.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = song.artist,
                    color = WhiteAlpha90,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            
            // Duration
            Text(
                text = song.duration,
                color = GraySubtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}