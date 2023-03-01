package dev.medetzhakupov.domain.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://www.rijksmuseum.nl/api/"

class RijksMuseumClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rijksMuseumService : RijksMuseumService by lazy {
        retrofit.create(RijksMuseumService::class.java)
    }
}