package dev.medetzhakupov.domain.usecase

import dev.medetzhakupov.domain.model.RijksDataUIModel
import dev.medetzhakupov.domain.repo.RijksMuseumRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RijksDataUIModelUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch rijks collection`() = runTest {
        val rijksDataUIModelList: List<RijksDataUIModel> = mockk()
        val repo: RijksMuseumRepo = mockk {
            coEvery { getRijksCollection(any()) } returns rijksDataUIModelList
        }

        val useCase = RijksCollectionUseCase(repo)

        assertEquals(rijksDataUIModelList, useCase.getRijksCollection(1))
    }
}