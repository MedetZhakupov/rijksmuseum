package dev.medetzhakupov.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import javax.inject.Inject

class PagerFactory @Inject constructor(
    private val rijksMuseumService: RijksMuseumService
) {

    fun providePager() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            RijksCollectionPagingSource(rijksMuseumService)
        })
}