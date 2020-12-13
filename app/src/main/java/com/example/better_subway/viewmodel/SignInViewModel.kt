package com.example.better_subway.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class SignInViewModel @ViewModelInject constructor(
    private val service : ServiceApi
): ViewModel() {

    val codeLiveData = MutableLiveData<String>()

    fun signIn(userId : String, password : String){
        viewModelScope.launch {
            val data = service.signIn(userId,password)
            codeLiveData.value = data
        }
    }
}