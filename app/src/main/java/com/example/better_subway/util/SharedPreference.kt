package com.example.better_subway.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context : Context) {

    val prefs : SharedPreferences =
        context.getSharedPreferences("userInfo",Context.MODE_PRIVATE)

    fun putCookies(cookies : HashSet<String>){
        prefs.edit().putStringSet("cookies",cookies).apply()
    }

    fun getCookies() : MutableSet<String>?{
        return prefs.getStringSet("cookies",HashSet<String>())
    }

    fun removeCookies(){
        prefs.edit().remove("cookies").apply()
    }


}