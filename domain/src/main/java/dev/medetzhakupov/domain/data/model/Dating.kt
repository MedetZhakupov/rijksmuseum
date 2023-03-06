package dev.medetzhakupov.domain.data.model

data class Dating(
    val period: Int,
    val presentingDate: String,
    val sortingDate: Int,
    val yearEarly: Int,
    val yearLate: Int
)