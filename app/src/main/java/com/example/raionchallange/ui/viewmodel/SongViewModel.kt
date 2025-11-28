package com.example.raionchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionchallange.data.model.Song
import com.example.raionchallange.data.repository.SongRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongViewModel(
    private val songRepository: SongRepository
) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadSongsByMood(moodId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                songRepository.getSongsByMoodId(moodId).collect { songList ->
                    _songs.value = songList
                    _isLoading.value = false // Stop loading after the first data emission
                }
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat lagu: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun loadAllSongs() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                songRepository.getAllSongs().collect { songList ->
                    _songs.value = songList
                    _isLoading.value = false // Stop loading after the first data emission
                }
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat lagu: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun addSong(song: Song) {
        viewModelScope.launch {
            try {
                songRepository.insertSong(song)
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menambah lagu: ${e.message}"
            }
        }
    }

    fun updateSong(song: Song) {
        viewModelScope.launch {
            try {
                songRepository.updateSong(song)
            } catch (e: Exception) {
                _errorMessage.value = "Gagal mengupdate lagu: ${e.message}"
            }
        }
    }

    fun deleteSong(id: Int) {
        viewModelScope.launch {
            try {
                songRepository.deleteSongById(id)
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menghapus lagu: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
