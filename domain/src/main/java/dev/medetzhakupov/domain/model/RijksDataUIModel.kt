package dev.medetzhakupov.domain.model

data class RijksDataUIModel(
    val artist: String,
    val artistsCollection: List<ArtistObjectUiModel>
)

data class ArtistObjectUiModel(
    val id: String,
    val objectNumber: String,
    val artist: String,
    val title: String,
    val longTitle: String,
    val headerImage: ImageUiModel,
    val fullImage: ImageUiModel,
)

data class ImageUiModel(
    val url: String,
    val height: Int,
    val width: Int,
)
