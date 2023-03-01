package dev.medetzhakupov.domain

import javax.inject.Inject

class RijksCollectionUseCase @Inject constructor(
    private val pagerFactory: PagerFactory
) {

    fun getCollection() = pagerFactory.providePager()
}