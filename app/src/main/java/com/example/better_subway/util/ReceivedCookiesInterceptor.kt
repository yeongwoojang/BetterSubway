package com.example.better_subway.util

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ReceivedCookiesInterceptor(context : Context) :
    Interceptor {
    val prefs: SharedPreference = SharedPreference(context)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies: HashSet<String> = HashSet<String>()

            for (header: String in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
//             sharedPreferences에 cookies를 넣어주는 작업을 수행
            prefs.putCookies(cookies)
        }
        return originalResponse
    }
}