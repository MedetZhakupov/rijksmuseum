package dev.medetzhakupov.domain.usecase

import dev.medetzhakupov.domain.model.RijksCollection
import dev.medetzhakupov.domain.repo.RijksMuseumRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RijksCollectionUseCaseTest {

    @Test
    fun `fetch rijks collection`() = runTest {
        val rijksCollectionList: List<RijksCollection> = mockk()
        val repo: RijksMuseumRepo = mockk {
            coEvery { getRijksCollection(any()) } returns rijksCollectionList
        }

        val useCase = RijksCollectionUseCase(repo)

        assertEquals(rijksCollectionList, useCase.getRijksCollection(1))
    }
}