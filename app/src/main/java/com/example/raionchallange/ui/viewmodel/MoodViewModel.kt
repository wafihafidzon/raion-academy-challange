package com.example.raionchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionchallange.data.model.Mood
import com.example.raionchallange.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodViewModel(
    private val moodRepository: MoodRepository
) : ViewModel() {

    private val _moods = MutableStateFlow<List<Mood>>(emptyList())
    val moods: StateFlow<List<Mood>> = _moods.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        checkAndSeedDatabase()
        loadMoods()
    }

    private fun checkAndSeedDatabase() {
        viewModelScope.launch {
            if (moodRepository.isDatabaseEmpty()) {
                moodRepository.seedDefaultMoods()
            }
        }
    }

    fun loadMoods() {
        viewModelScope.launch {
            try {
                moodRepository.getAllMoods().collect { moodList ->
                    _moods.value = moodList
                    _isLoading.value = false // Matikan loading setelah data pertama diterima
                }
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat data: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun addMood(name: String, description: String) {
        viewModelScope.launch {
            try {
                moodRepository.createMoodWithRandomGradient(
                    name = name,
                    description = description,
                    iconName = "ic_mood_default" // Default icon
                )
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menambah mood: ${e.message}"
            }
        }
    }

    fun updateMood(id: Int, name: String, description: String) {
        viewModelScope.launch {
            try {
                val currentMood = _moods.value.find { it.id == id }
                currentMood?.let { mood ->
                    val updatedMood = mood.copy(
                        name = name,
                        description = description
                    )
                    moodRepository.updateMood(updatedMood)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Gagal mengupdate mood: ${e.message}"
            }
        }
    }

    fun deleteMood(id: Int) {
        viewModelScope.launch {
            try {
                moodRepository.deleteMoodById(id)
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menghapus mood: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
