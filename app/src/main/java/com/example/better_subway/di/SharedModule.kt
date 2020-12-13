package com.example.better_subway.di

import android.content.Context
import com.example.better_subway.util.SharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object SharedModule {

    @Provides
    fun getSharedPreferences(@ApplicationContext context : Context) : SharedPreference {
        return SharedPreference(context)

    }
}