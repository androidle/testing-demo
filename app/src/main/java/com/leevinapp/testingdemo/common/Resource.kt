package com.leevinapp.testingdemo.common

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String? = null) : Resource<Nothing>()
}
