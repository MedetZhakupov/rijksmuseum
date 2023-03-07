package dev.medetzhakupov.domain.data.model

data class ArtObjectDetail(
    val dating: Dating,
    val description: String,
    val dimensions: List<Dimension>,
    val hasImage: Boolean,
    val historicalPersons: List<String>,
    val id: String,
    val longTitle: String,
    val materials: List<String>,
    val objectCollection: List<String>,
    val objectNumber: String,
    val objectTypes: List<String>,
    val physicalMedium: String,
    val plaqueDescriptionDutch: String,
    val plaqueDescriptionEnglish: String,
    val principalMaker: String,
    val principalMakers: List<PrincipalMaker>,
    val principalOrFirstMaker: String,
    val productionPlaces: List<String>,
    val showImage: Boolean,
    val subTitle: String,
    val title: String,
    val titles: List<String>,
    val webImage: Image
)