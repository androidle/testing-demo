package com.leevinapp.testingdemo.repository

import com.leevinapp.testingdemo.repository.model.GithubRepo

interface UserDataRepository {
    suspend fun getUserData(): List<GithubRepo>
}
