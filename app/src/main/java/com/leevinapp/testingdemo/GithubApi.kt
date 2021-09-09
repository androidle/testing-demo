package com.leevinapp.testingdemo

import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users/list/repos")
    fun testFetch(): Call<List<GithubRepoResponse>>
}
