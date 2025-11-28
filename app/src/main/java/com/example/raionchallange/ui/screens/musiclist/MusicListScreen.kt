package com.example.raionchallange.ui.screens.musiclist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionchallange.data.database.MoodDatabase
import com.example.raionchallange.data.model.Mood
import com.example.raionchallange.data.model.Song
import com.example.raionchallange.data.repository.SongRepository
import com.example.raionchallange.ui.components.dialogs.AddSongDialog
import com.example.raionchallange.ui.components.dialogs.DeleteSongDialog
import com.example.raionchallange.ui.components.dialogs.EditSongDialog
import com.example.raionchallange.ui.theme.BackgroundGradientEnd
import com.example.raionchallange.ui.theme.BackgroundGradientStart
import com.example.raionchallange.ui.theme.GraySubtitle
import com.example.raionchallange.ui.theme.WhiteAlpha90
import com.example.raionchallange.ui.viewmodel.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListScreen(
    mood: Mood,
    onBackClick: () -> Unit,
    onSongClick: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val database = remember { MoodDatabase.getDatabase(context) }
    val songRepository = remember { SongRepository(database.songDao()) }
    val songViewModel = remember { SongViewModel(songRepository) }
    
    val songs by songViewModel.songs.collectAsState()
    val isLoading by songViewModel.isLoading.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    LaunchedEffect(mood.id) {
        songViewModel.loadSongsByMood(mood.id)
    }
    
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
                            text = "Musik ${mood.name}",
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

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(songs) { song ->
                        SongCard(
                            song = song,
                            onEditClick = { 
                                selectedSong = song
                                showEditDialog = true 
                            },
                            onDeleteClick = { 
                                selectedSong = song
                                showDeleteDialog = true 
                            },
                            onPlayClick = {
                                onSongClick(song)
                            }
                        )
                    }
                }
            }
        }
        
        // FAB for adding songs
        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.White.copy(alpha = 0.9f)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add Song",
                tint = Color.Black
            )
        }
    }

    if (showAddDialog) {
        AddSongDialog(
            moodId = mood.id,
            onDismiss = { showAddDialog = false },
            onConfirm = { newSong ->
                songViewModel.addSong(newSong)
                showAddDialog = false
            }
        )
    }
    
    if (showEditDialog && selectedSong != null) {
        EditSongDialog(
            song = selectedSong!!,
            onDismiss = { 
                showEditDialog = false
                selectedSong = null
            },
            onConfirm = { updatedSong ->
                songViewModel.updateSong(updatedSong)
                showEditDialog = false
                selectedSong = null
            }
        )
    }
    
    if (showDeleteDialog && selectedSong != null) {
        DeleteSongDialog(
            songTitle = selectedSong!!.title,
            onDismiss = { 
                showDeleteDialog = false
                selectedSong = null
            },
            onConfirm = {
                songViewModel.deleteSong(selectedSong!!.id)
                showDeleteDialog = false
                selectedSong = null
            }
        )
    }
}

@Composable
fun SongCard(
    song: Song,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showMenu by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onPlayClick() },
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
            
            // Platform Info
            Text(
                text = song.platform.displayName,
                color = GraySubtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // Menu Button
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "More Options",
                        tint = Color.White
                    )
                }
                
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { 
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Edit")
                            }
                        },
                        onClick = {
                            showMenu = false
                            onEditClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { 
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Delete, 
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "Hapus",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        onClick = {
                            showMenu = false
                            onDeleteClick()
                        }
                    )
                }
            }
        }
    }
}