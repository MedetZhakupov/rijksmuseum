package dev.medetzhakupov.domain.data.remote

import dev.medetzhakupov.domain.data.model.RijksData
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "0fiuZFh4"

interface RijksMuseumService {

    @GET("nl/collection?key=${API_KEY}&ps=20&s=artist")
    suspend fun getCollection(@Query("p") page: Int): RijksData
}