package com.example.better_subway.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.better_subway.repository.ServiceApi
import com.example.better_subway.util.SharedPreference

class MainViewModel @ViewModelInject constructor(
    private val service : ServiceApi,
    private val prefs : SharedPreference
) : ViewModel(){

    fun getLoginSession(): String {
        var userSession: String = " "
        val iterator = prefs.getCookies()?.iterator()
        if (iterator != null) {
            while (iterator.hasNext()) {
                userSession = iterator.next()
                userSession = userSession.split(";")[0].split("=")[1]
                Log.d("SESSION", "getLoginSession: $userSession")
            }
        }
        return userSession

    }

}