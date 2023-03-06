package dev.medetzhakupov.rijksmuseum.ui.artistart

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.medetzhakupov.domain.data.model.ArtObjectDetail
import dev.medetzhakupov.domain.usecase.ArtistArtUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistArtViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val artistArtUseCase: ArtistArtUseCase
) : ViewModel() {

    private val objectNumber: String = checkNotNull(savedStateHandle["objectNumber"])

    private val _viewState =
        MutableStateFlow<ArtistArtViewState>(ArtistArtViewState.Loading)
    val viewState: StateFlow<ArtistArtViewState>
        get() {
            return _viewState
        }

    init {
        viewModelScope.launch {
            try {
                _viewState.value = ArtistArtViewState.Loading
                val artObjectDetail = artistArtUseCase.getArtistArtObject(objectNumber = objectNumber).artObject
                _viewState.value = ArtistArtViewState.Loaded(artObjectDetail)
            } catch (error: Exception) {
                _viewState.value = ArtistArtViewState.Error
            }
        }
    }
}

sealed class ArtistArtViewState {
    object Loading : ArtistArtViewState()
    data class Loaded(
        val artist: ArtObjectDetail,
    ) : ArtistArtViewState()
    object Error : ArtistArtViewState()
}