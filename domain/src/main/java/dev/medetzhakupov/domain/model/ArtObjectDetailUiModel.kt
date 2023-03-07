package dev.medetzhakupov.domain.model

import dev.medetzhakupov.domain.data.model.Image

data class ArtObjectDetailUiModel(
    val dating: DatingUiModel,
    val description: String,
    val materials: List<String>,
    val objectTypes: List<String>,
    val principalOrFirstMaker: String,
    val productionPlaces: List<String>,
    val subTitle: String,
    val title: String,
    val webImage: Image
)

data class DatingUiModel(
    val yearEarly: Int,
    val yearLate: Int
)