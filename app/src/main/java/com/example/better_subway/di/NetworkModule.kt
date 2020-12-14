package com.example.better_subway.di

import android.app.Service
import android.content.Context
import com.example.better_subway.repository.ServiceApi
import com.example.better_subway.util.AddCookiesInterceptor
import com.example.better_subway.util.ReceivedCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun getClient(@ApplicationContext context : Context) : ServiceApi{

        val okHttpClient = OkHttpClient.Builder()
            //쿠키를 sharedPreferences에 저장하고 가져온다.
            .addNetworkInterceptor(AddCookiesInterceptor(context))
            .addNetworkInterceptor(ReceivedCookiesInterceptor(context))
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ServiceApi::class.java)
    }
}