package dev.medetzhakupov.domain.repo

import dev.medetzhakupov.domain.data.model.ArtistArtObject
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import dev.medetzhakupov.domain.mapper.mapToUiModel
import dev.medetzhakupov.domain.model.RijksCollection

class RijksMuseumRepo(
    private val rijksMuseumService: RijksMuseumService
) {
    suspend fun getRijksCollection(page: Int): List<RijksCollection> {
        val rijksData = rijksMuseumService.getCollection(page)

        return rijksData.artObjects
            .filter { it.hasImage }
            .groupBy { it.principalOrFirstMaker }
            .map { entry -> entry.mapToUiModel() }
    }

    suspend fun getArtistArtObject(objectNumber: String): ArtistArtObject {
        val artObject = rijksMuseumService.getArtistArtObject(objectNumber)

        return artObject
    }
}