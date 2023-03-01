package dev.medetzhakupov.domain

import androidx.paging.PagingData
import dev.medetzhakupov.domain.data.model.ArtObject
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RijksCollectionUseCaseTest {

    @Test
    fun `fetch rijks collection`() {
        val flowPagingData: Flow<PagingData<ArtObject>> = flowOf(mockk())
        val pagerFactory: PagerFactory = mockk {
            every { providePager() } returns flowPagingData
        }

        val useCase = RijksCollectionUseCase(
            pagerFactory = pagerFactory
        )

        assertEquals(flowPagingData, useCase.getCollection())
    }
}