package com.leevinapp.testingdemo


import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users/list")
    fun testFetch(): Call<GithubResponse>
}