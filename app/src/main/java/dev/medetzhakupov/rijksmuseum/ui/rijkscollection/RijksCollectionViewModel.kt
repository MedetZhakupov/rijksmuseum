package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.medetzhakupov.domain.RijksCollectionUseCase
import dev.medetzhakupov.domain.data.model.ArtObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RijksCollectionViewModel @Inject constructor(
    private val rijksCollectionUseCase: RijksCollectionUseCase
): ViewModel() {

    fun getRijksCollection(): Flow<PagingData<ArtObject>> = rijksCollectionUseCase.getCollection()

}