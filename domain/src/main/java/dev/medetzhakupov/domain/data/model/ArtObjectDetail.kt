package dev.medetzhakupov.domain.data.model

data class ArtObjectDetail(
    val dating: Dating,
    val description: String,
    val hasImage: Boolean,
    val id: String,
    val materials: List<String>,
    val objectNumber: String,
    val objectTypes: List<String>,
    val principalOrFirstMaker: String,
    val productionPlaces: List<String>,
    val subTitle: String,
    val title: String,
    val webImage: Image
) {
    data class Dating(
        val period: Int,
        val presentingDate: String,
        val sortingDate: Int,
        val yearEarly: Int,
        val yearLate: Int
    )
}