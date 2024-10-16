package com.example.anime.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.data.AnimeRepository
import com.example.anime.model.Anime
import com.example.anime.model.FakeAnime
import com.example.anime.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AnimeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FakeAnime>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FakeAnime>>>
        get() =_uiState

    fun getAllAnime() {
        viewModelScope.launch {
            repository.getAllAnime()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { fakeAnime ->
                    _uiState.value = UiState.Success(fakeAnime)
                }
        }
    }
}