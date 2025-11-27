package com.example.raionchallange.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.ui.theme.WhiteAlpha90

@Composable
fun MoodCard(
    mood: MoodType,
    onClick: (MoodType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(mood.gradientStart, mood.gradientEnd)
                )
            )
            .clickable { onClick(mood) }
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = mood.emoji,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        // Text Content
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = mood.displayName,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.3125).sp
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = mood.description,
                color = WhiteAlpha90,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.1504).sp,
                lineHeight = 20.sp
            )
        }
    }
}