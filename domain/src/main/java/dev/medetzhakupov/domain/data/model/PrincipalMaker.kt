package dev.medetzhakupov.domain.data.model

data class PrincipalMaker(
    val dateOfBirth: String,
    val dateOfDeath: String,
    val labelDesc: String,
    val name: String,
    val productionPlaces: List<String>,
)