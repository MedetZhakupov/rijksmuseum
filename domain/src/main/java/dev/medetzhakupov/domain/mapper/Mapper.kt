package dev.medetzhakupov.domain.mapper

import dev.medetzhakupov.domain.data.model.ArtObject
import dev.medetzhakupov.domain.model.ImageUiModel
import dev.medetzhakupov.domain.model.ArtistObjectUiModel
import dev.medetzhakupov.domain.model.RijksDataUIModel

fun Map.Entry<String, List<ArtObject>>.mapToUiModel() = RijksDataUIModel(
        artist = key,
        artistsCollection = value.map { art ->
            ArtistObjectUiModel(
                id = art.id,
                objectNumber = art.objectNumber,
                artist = art.principalOrFirstMaker,
                title = art.title,
                longTitle = art.longTitle,
                headerImage = ImageUiModel(
                    url = art.headerImage.url,
                    height = art.headerImage.height,
                    width = art.headerImage.width,
                ),
                fullImage = ImageUiModel(
                    url = art.webImage.url,
                    height = art.webImage.height,
                    width = art.webImage.width,
                ),
            )
        }
    )