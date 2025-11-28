package com.example.raionchallange.ui.screens.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionchallange.data.model.Mood
import com.example.raionchallange.ui.components.AddMoodDialog
import com.example.raionchallange.ui.components.DatabaseMoodCard
import com.example.raionchallange.ui.components.DeleteConfirmationDialog
import com.example.raionchallange.ui.components.EditMoodDialog
import com.example.raionchallange.ui.theme.BackgroundGradientEnd
import com.example.raionchallange.ui.theme.BackgroundGradientStart
import com.example.raionchallange.ui.theme.GraySubtitle
import com.example.raionchallange.ui.viewmodel.MoodViewModel

@Composable
fun MoodSelectionScreen(
    viewModel: MoodViewModel,
    onMoodSelected: (Mood) -> Unit,
    modifier: Modifier = Modifier
) {
    val moods by viewModel.moods.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedMood by remember { mutableStateOf<Mood?>(null) }

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(14.dp))

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

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(32.dp),
                        color = Color.White
                    )
                }
                moods.isEmpty() -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "Belum ada mood",
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Tambah mood pertama Anda",
                            color = Color.White.copy(alpha = 0.5f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(moods) { mood ->
                            DatabaseMoodCard(
                                mood = mood,
                                onClick = { onMoodSelected(mood) },
                                onEdit = {
                                    selectedMood = mood
                                    showEditDialog = true
                                },
                                onDelete = {
                                    selectedMood = mood
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Tambah Mood"
            )
        }
    }

    // Dialogs
    if (showAddDialog) {
        AddMoodDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, description ->
                viewModel.addMood(name, description)
                showAddDialog = false
            }
        )
    }

    selectedMood?.let { mood ->
        if (showEditDialog) {
            EditMoodDialog(
                mood = mood,
                onDismiss = { showEditDialog = false },
                onConfirm = { id, name, description ->
                    viewModel.updateMood(id, name, description)
                    showEditDialog = false
                    selectedMood = null
                }
            )
        }

        if (showDeleteDialog) {
            DeleteConfirmationDialog(
                itemName = mood.name,
                itemType = "mood",
                onDismiss = { showDeleteDialog = false },
                onConfirm = {
                    viewModel.deleteMood(mood.id)
                    showDeleteDialog = false
                    selectedMood = null
                }
            )
        }
    }

    // Error message
    errorMessage?.let { message ->
        LaunchedEffect(message) {
            // You can show a Snackbar here if needed
            viewModel.clearErrorMessage()
        }
    }
}
