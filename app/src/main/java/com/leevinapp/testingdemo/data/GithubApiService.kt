package com.leevinapp.testingdemo.data

import com.leevinapp.testingdemo.data.model.GithubRepoResponse
import retrofit2.http.GET

interface GithubApiService {
    @GET("users/list/repos")
    suspend fun testFetch(): List<GithubRepoResponse>
}
