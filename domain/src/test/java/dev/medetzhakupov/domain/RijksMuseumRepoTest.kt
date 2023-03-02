package dev.medetzhakupov.domain

import dev.medetzhakupov.domain.data.model.RijksData
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RijksMuseumRepoTest {

    private val rijksData: RijksData = mockk()
    private val rijksMuseumService: RijksMuseumService = mockk {
        coEvery { getCollection(any()) } returns rijksData
    }
    private val repo = RijksMuseumRepo(rijksMuseumService)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should get rijks collection from api`() = runTest {
        assertEquals(rijksData, repo.getRijksCollection(1))
    }
}