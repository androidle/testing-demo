package com.leevinapp.testingdemo.repository

import com.leevinapp.testingdemo.data.GithubApiService
import com.leevinapp.testingdemo.repository.model.GithubRepo

class UserDataRepository(
    private val githubApiService: GithubApiService
) {
    suspend fun getUserData(): List<GithubRepo> {
        val responses = githubApiService.testFetch()
        return responses.map { response ->
            response.toModel()
        }
    }
}
