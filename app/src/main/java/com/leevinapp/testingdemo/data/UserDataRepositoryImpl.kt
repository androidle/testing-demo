package com.leevinapp.testingdemo.data

import com.leevinapp.testingdemo.repository.UserDataRepository
import com.leevinapp.testingdemo.repository.model.GithubRepo

class UserDataRepositoryImpl(private val githubApiService: GithubApiService) : UserDataRepository {
    override suspend fun getUserData(): List<GithubRepo> {
        val responses = githubApiService.testFetch()
        return responses.map { response ->
            response.toModel()
        }
    }
}
