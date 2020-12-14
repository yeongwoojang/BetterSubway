package com.example.better_subway.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.better_subway.repository.ServiceApi
import com.example.better_subway.util.SharedPreference

class HomeViewModel @ViewModelInject constructor(
    private val service : ServiceApi,
    private val prefs : SharedPreference
): ViewModel(){

    fun logout() {
        //로그아웃읋하면 자동로그인을 해제하기 위해 쿠키를 삭제한다.
        prefs.removeCookies()
    }
}