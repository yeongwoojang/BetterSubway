package com.example.better_subway.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor(context : Context) : Interceptor {

    val prefs : SharedPreference = SharedPreference(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = prefs.getCookies()

        if (cookies != null) {
            for(cookie : String in cookies){
                builder.addHeader("Cookie",cookie)
            }
        }

        //Web,Android,IOS 구분을 위해 User-Agent셋팅
        builder.removeHeader("User-Agent").addHeader("User-Agent","Android")

        return chain.proceed(builder.build())
    }
}