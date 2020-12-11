package com.example.better_subway.repository

import com.example.better_subway.model.vo.StationInfo
import retrofit2.http.*

interface ServiceApi {

    companion object{
        val BASE_URL: String = "http://ec2-15-164-129-208.ap-northeast-2.compute.amazonaws.com:3000"
    }


    @GET("/android/stationInfo")
    suspend fun getStationInfo() : StationInfo
}