package com.leevinapp.testingdemo.common

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String? = null) : Result<Nothing>()
}
