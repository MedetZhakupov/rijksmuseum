package dev.medetzhakupov.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRijksMuseumService(): RijksMuseumService =
        Retrofit.Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RijksMuseumService::class.java)
}