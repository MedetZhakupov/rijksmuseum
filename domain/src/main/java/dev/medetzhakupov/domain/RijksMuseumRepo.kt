package dev.medetzhakupov.domain

import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import javax.inject.Inject

class RijksMuseumRepo @Inject constructor(
    private val rijksMuseumService: RijksMuseumService
) {
    suspend fun getRijksCollection(page: Int) = rijksMuseumService.getCollection(page)
}