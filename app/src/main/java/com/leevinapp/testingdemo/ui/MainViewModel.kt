package com.leevinapp.testingdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leevinapp.testingdemo.common.Result
import com.leevinapp.testingdemo.repository.UserDataRepository
import com.leevinapp.testingdemo.repository.model.GithubRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {
    private val _userDataResult: MutableLiveData<Result<List<GithubRepo>>> = MutableLiveData()
    val userDataResult = _userDataResult

    fun fetchData() {
        viewModelScope.launch {
            runCatching {
                userDataRepository.getUserData()
            }.onSuccess {
                _userDataResult.value = Result.Success(it)
            }.onFailure {
                _userDataResult.value = Result.Error(it.message)
            }
        }
    }
}
