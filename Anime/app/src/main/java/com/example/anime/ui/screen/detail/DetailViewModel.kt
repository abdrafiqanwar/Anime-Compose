package com.example.anime.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.data.AnimeRepository
import com.example.anime.model.FakeAnime
import com.example.anime.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AnimeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FakeAnime>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FakeAnime>>
        get() = _uiState

    fun getAnimeById(animeId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAnimeById(animeId))
        }
    }
}