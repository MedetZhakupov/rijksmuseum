package dev.medetzhakupov.domain.data.model

data class ArtObjectDetail(
    val dating: Dating,
    val description: String,
    val dimensions: List<Dimension>,
    val hasImage: Boolean,
    val id: String,
    val materials: List<String>,
    val objectNumber: String,
    val objectTypes: List<String>,
    val principalMakers: List<PrincipalMaker>,
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

    data class Dimension(
        val part: Any?,
        val precision: Any?,
        val type: String,
        val unit: String,
        val value: String
    )

    data class PrincipalMaker(
        val dateOfBirth: String,
        val dateOfDeath: String,
        val labelDesc: String,
        val name: String,
        val productionPlaces: List<String>,
    )
}