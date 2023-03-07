package dev.medetzhakupov.domain.mapper

import dev.medetzhakupov.domain.data.model.ArtObject
import dev.medetzhakupov.domain.data.model.ArtObjectDetail
import dev.medetzhakupov.domain.model.*

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

fun ArtObjectDetail.mapToUiModel() = ArtObjectDetailUiModel(
    dating = DatingUiModel(
        yearEarly = dating.yearEarly,
        yearLate = dating.yearLate,
    ),
    description = description,
    materials = materials,
    objectTypes = objectTypes,
    principalOrFirstMaker = principalOrFirstMaker,
    productionPlaces = productionPlaces,
    subTitle = subTitle,
    title = title,
    webImage = webImage,
)