package dev.medetzhakupov.domain.repo

import dev.medetzhakupov.domain.data.model.ArtistArtObject
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import dev.medetzhakupov.domain.model.Art
import dev.medetzhakupov.domain.model.ArtistCollectionItem
import dev.medetzhakupov.domain.model.RijksCollection

class RijksMuseumRepo(
    private val rijksMuseumService: RijksMuseumService
) {
    suspend fun getRijksCollection(page: Int): List<RijksCollection> {
        val rijksData = rijksMuseumService.getCollection(page)

        return rijksData.artObjects
            .filter { it.hasImage }
            .groupBy { it.principalOrFirstMaker }
            .map { entry ->
                RijksCollection(
                    artist = entry.key,
                    artistsCollection = entry.value.map { art ->
                        ArtistCollectionItem(
                            id = art.id,
                            objectNumber = art.objectNumber,
                            artist = art.principalOrFirstMaker,
                            title = art.title,
                            longTitle = art.longTitle,
                            headerImage = Art(
                                url = art.headerImage.url,
                                height = art.headerImage.height,
                                width = art.headerImage.width,
                            ),
                            fullImage = Art(
                                url = art.webImage.url,
                                height = art.webImage.height,
                                width = art.webImage.width,
                            ),
                        )
                    }
                )
            }
    }

    suspend fun getArtistArtObject(objectNumber: String): ArtistArtObject {
        val artObject = rijksMuseumService.getArtistArtObject(objectNumber)

        return artObject
    }
}