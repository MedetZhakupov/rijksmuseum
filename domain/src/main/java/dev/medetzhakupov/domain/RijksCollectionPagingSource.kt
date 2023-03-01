package dev.medetzhakupov.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.medetzhakupov.domain.data.model.ArtObject
import dev.medetzhakupov.domain.data.remote.RijksMuseumService

class RijksCollectionPagingSource(
    private val rijksMuseumService: RijksMuseumService,
): PagingSource<Int, ArtObject>() {
    override fun getRefreshKey(state: PagingState<Int, ArtObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObject> {
        return try {
            val page = params.key ?: 1
            val response = rijksMuseumService.getCollection(page = page)

            LoadResult.Page(
                data = response.artObjects,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.artObjects.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}