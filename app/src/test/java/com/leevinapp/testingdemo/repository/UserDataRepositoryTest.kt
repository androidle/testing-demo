package com.leevinapp.testingdemo.repository

import com.leevinapp.testingdemo.data.GithubApiService
import com.leevinapp.testingdemo.data.model.GithubRepoResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserDataRepositoryTest {

    private lateinit var repository: UserDataRepository
    @MockK
    lateinit var apiService: GithubApiService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserDataRepository(apiService)
    }

    @Test
    fun `when getUserData success then return GithubRepos`() {
        runBlocking {
            // Given
            val givenResponse = mutableListOf(
                GithubRepoResponse(
                    archive_url = "https://archiveurls",
                    archived = false,
                    assignees_url = "",
                    blobs_url = "",
                    branches_url = "branch"
                )
            )
            coEvery { apiService.testFetch() } returns givenResponse

            // When
            val result = repository.getUserData()

            // Then
            coVerify { apiService.testFetch() }
            assert(result.size == givenResponse.size)
            assert(result.first().archiveUrl == givenResponse.first().archive_url)
        }
    }
}
