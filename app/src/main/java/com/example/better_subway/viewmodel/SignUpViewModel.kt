package com.example.better_subway.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class SignUpViewModel @ViewModelInject constructor(
    private val service : ServiceApi
): ViewModel(){

    val codeLiveData = MutableLiveData<String>()

    fun signUp(userName : String, userId : String, password : String){
        viewModelScope.launch {
            val data = service.signUp(userName,userId,password)
            codeLiveData.value = data
        }
    }
}