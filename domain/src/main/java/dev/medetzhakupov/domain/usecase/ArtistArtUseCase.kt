package dev.medetzhakupov.domain.usecase

import dev.medetzhakupov.domain.repo.RijksMuseumRepo
import javax.inject.Inject

class ArtistArtUseCase @Inject constructor(
    private val rijksMuseumRepo: RijksMuseumRepo
) {
    suspend fun getArtistArtObject(objectNumber: String) =
        rijksMuseumRepo.getArtistArtObject(objectNumber)
}