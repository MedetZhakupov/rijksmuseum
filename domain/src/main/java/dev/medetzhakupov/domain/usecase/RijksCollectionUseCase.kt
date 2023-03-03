package dev.medetzhakupov.domain.usecase

import dev.medetzhakupov.domain.repo.RijksMuseumRepo
import javax.inject.Inject

class RijksCollectionUseCase @Inject constructor(
    private val rijksMuseumRepo: RijksMuseumRepo
) {
    suspend fun getRijksCollection(page: Int) = rijksMuseumRepo.getRijksCollection(page)
}