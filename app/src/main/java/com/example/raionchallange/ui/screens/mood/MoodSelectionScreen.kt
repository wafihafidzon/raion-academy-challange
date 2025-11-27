package com.example.raionchallange.ui.screens.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.data.repository.MusicRepository
import com.example.raionchallange.ui.components.MoodCard
import com.example.raionchallange.ui.theme.BackgroundGradientEnd
import com.example.raionchallange.ui.theme.BackgroundGradientStart
import com.example.raionchallange.ui.theme.GraySubtitle

@Composable
fun MoodSelectionScreen(
    onMoodSelected: (MoodType) -> Unit,
    modifier: Modifier = Modifier
) {
    val moods = MusicRepository.getAllMoods()
    
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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Header Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                // Main Title with Gradient
                Text(
                    text = "Bagaimana Perasaanmu Hari Ini?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    letterSpacing = (-0.3125).sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Subtitle
                Text(
                    text = "Pilih mood-mu dan temukan musik yang sempurna",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    letterSpacing = (-0.1504).sp,
                    color = GraySubtitle,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Mood Cards
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(moods) { mood ->
                    MoodCard(
                        mood = mood,
                        onClick = onMoodSelected
                    )
                }
            }
        }
    }
}