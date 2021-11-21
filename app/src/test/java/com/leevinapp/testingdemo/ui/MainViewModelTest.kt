package com.leevinapp.testingdemo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leevinapp.testingdemo.common.Resource
import com.leevinapp.testingdemo.getOrAwaitValue
import com.leevinapp.testingdemo.repository.UserDataRepository
import com.leevinapp.testingdemo.repository.model.GithubRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @MockK
    lateinit var userDataRepository: UserDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(userDataRepository)
    }

    @Test
    fun `fetch data when success response`() {
        // Given
        val dummyListData = getMockListData()
        coEvery { userDataRepository.getUserData() } returns dummyListData
        // When
        mainViewModel.fetchData()
        // Then
        coVerify(exactly = 1) { userDataRepository.getUserData() }
        assert(mainViewModel.userDataResult.getOrAwaitValue() == Resource.Success(dummyListData))
    }

    @Test
    fun `fetch data when failure response`() {
        // Given
        val message = "Network error"
        coEvery { userDataRepository.getUserData() } throws IllegalArgumentException(message)
        // When
        mainViewModel.fetchData()
        // Then
        coVerify(exactly = 1) { userDataRepository.getUserData() }
        assert(mainViewModel.userDataResult.getOrAwaitValue() == Resource.Error(message))
    }

    private fun getMockListData(): List<GithubRepo> {
        return mutableListOf(
            GithubRepo(
                archive_url = "https://api.github.com/repos/list/yiitutorial/{archive_format}{/ref}",
                archived = false,
                assignees_url = null,
                blobs_url = null,
                branches_url = "https://api.github.com/repos/list/yiitutorial/branches{/branch}"
            )
        )
    }
}
