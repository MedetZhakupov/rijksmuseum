package dev.medetzhakupov.domain.data.model

data class ArtObject(
    val hasImage: Boolean,
    val headerImage: Image,
    val id: String,
    val longTitle: String,
    val objectNumber: String,
    val principalOrFirstMaker: String,
    val showImage: Boolean,
    val title: String,
    val webImage: Image
)