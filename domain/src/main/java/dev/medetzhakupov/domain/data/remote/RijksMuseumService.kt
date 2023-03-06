package dev.medetzhakupov.domain.data.remote

import dev.medetzhakupov.domain.data.model.ArtistArtObject
import dev.medetzhakupov.domain.data.model.RijksData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "0fiuZFh4"

interface RijksMuseumService {

    @GET("en/collection?key=${API_KEY}&ps=20&s=artist&imgonly=true&toppieces=true")
    suspend fun getCollection(@Query("p") page: Int): RijksData

    @GET("en/collection/{objectNumber}?key=${API_KEY}")
    suspend fun getArtistArtObject(@Path("objectNumber") objectNumber: String): ArtistArtObject
}