package dev.medetzhakupov.domain.repo

import dev.medetzhakupov.domain.data.model.*
import dev.medetzhakupov.domain.data.remote.RijksMuseumService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class RijksMuseumRepoTest {

    private val rijksData = RijksData(
        count = 1,
        artObjects = listOf(
            ArtObject(
                id = "id",
                hasImage = true,
                headerImage = HeaderImage(
                    guid = "guid",
                    height = 500,
                    offsetPercentageX = 0,
                    offsetPercentageY = 0,
                    url = "url",
                    width = 500,
                ),
                links = Links(self = "self", web = "web"),
                longTitle = "longTitle",
                objectNumber = "12",
                permitDownload = true,
                principalOrFirstMaker = "artis name",
                productionPlaces = listOf("Amsterdam"),
                showImage = true,
                title = "title",
                webImage = WebImage(
                    guid = "guid",
                    height = 500,
                    offsetPercentageX = 0,
                    offsetPercentageY = 0,
                    url = "url",
                    width = 500,
                )
            )
        )
    )
    private val rijksMuseumService: RijksMuseumService = mockk {
        coEvery { getCollection(any()) } returns rijksData
    }
    private val repo = RijksMuseumRepo(rijksMuseumService)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should get rijks collection from api`() = runTest {
        assertTrue(repo.getRijksCollection(1).size == 1)
    }
}