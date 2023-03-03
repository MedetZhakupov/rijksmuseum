package dev.medetzhakupov.domain.model

data class RijksCollection(
    val artist: String,
    val artistsCollection: List<ArtistCollectionItem>
)

data class ArtistCollectionItem(
    val id: String,
    val artist: String,
    val title: String,
    val longTitle: String,
    val headerImage: Art,
    val fullImage: Art,
)

data class Art(
    val url: String,
    val height: Int,
    val width: Int,
)
