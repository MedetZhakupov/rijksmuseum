package dev.medetzhakupov.domain.mapper

import dev.medetzhakupov.domain.data.model.ArtObject
import dev.medetzhakupov.domain.model.Art
import dev.medetzhakupov.domain.model.ArtistCollectionItem
import dev.medetzhakupov.domain.model.RijksCollection

fun Map.Entry<String, List<ArtObject>>.mapToUiModel() = RijksCollection(
        artist = key,
        artistsCollection = value.map { art ->
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