package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.medetzhakupov.domain.model.RijksDataUIModel
import dev.medetzhakupov.domain.usecase.RijksCollectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RijksCollectionViewModel @Inject constructor(
    private val rijksCollectionUseCase: RijksCollectionUseCase
) : ViewModel() {

    private var currentPage = 1
    private val _viewState =
        MutableStateFlow<RijksCollectionViewState>(RijksCollectionViewState.Loading)
    val viewState: StateFlow<RijksCollectionViewState>
        get() {
            return _viewState
        }

    init {
        getRijksCollection(currentPage)
    }

    fun loadMoreRijksCollection() = getRijksCollection(++currentPage)

    private fun getRijksCollection(page: Int) {
        viewModelScope.launch {
            if (page > 1) {
                (_viewState.value as? RijksCollectionViewState.Loaded)?.let {
                    _viewState.value = it.copy(loadingMore = true)
                }
            }
            val newList = mutableListOf<RijksDataUIModel>()
            (_viewState.value as? RijksCollectionViewState.Loaded)?.rijksDataUIModelList?.let {
                newList.addAll(it)
            }
            newList.addAll(rijksCollectionUseCase.getRijksCollection(page))
            _viewState.value = RijksCollectionViewState.Loaded(newList)
        }
    }
}

sealed class RijksCollectionViewState {
    object Loading : RijksCollectionViewState()
    data class Loaded(
        val rijksDataUIModelList: List<RijksDataUIModel>,
        val loadingMore: Boolean = false,
    ) : RijksCollectionViewState()

    object Error : RijksCollectionViewState()
}