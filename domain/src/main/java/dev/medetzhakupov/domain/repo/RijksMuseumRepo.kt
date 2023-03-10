package dev.medetzhakupov.domain.repo

import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import dev.medetzhakupov.domain.mapper.mapToUiModel
import dev.medetzhakupov.domain.model.ArtObjectDetailUiModel
import dev.medetzhakupov.domain.model.RijksDataUIModel

class RijksMuseumRepo(
    private val rijksMuseumService: RijksMuseumService
) {
    suspend fun getRijksCollection(page: Int): List<RijksDataUIModel> {
        val rijksData = rijksMuseumService.getCollection(page)

        return rijksData.artObjects
            .filter { it.hasImage }
            .groupBy { it.principalOrFirstMaker }
            .map { entry -> entry.mapToUiModel() }
    }

    suspend fun getArtistArtObject(objectNumber: String): ArtObjectDetailUiModel {
        return rijksMuseumService.getArtistArtObject(objectNumber).artObject.mapToUiModel()
    }
}