package com.example.better_subway.repository

import com.example.better_subway.model.vo.ArrivalInfo
import com.example.better_subway.model.vo.SeatInfo
import com.example.better_subway.model.vo.StationInfo
import retrofit2.http.*

interface ServiceApi {

    companion object {
        val BASE_URL: String = "http://ec2-15-164-129-208.ap-northeast-2.compute.amazonaws.com:3000"
    }


    //지하철 노선들을 서버에 요청
    @GET("/android/stationInfo")
    suspend fun getStationInfo(): StationInfo

    //지하철 도착정보를 서버에 요청
    @GET("/android/arrivalTime")
    suspend fun getArrivalTime(@Query("station") station: String): ArrivalInfo

    //좌석정보를 서버에 요청
    @GET("/android/seatInfo")
    suspend fun getSeatInfo(
        @Query("trainNum") trainNum: Int,
        @Query("blockNum") block: String
    ): SeatInfo

    @FormUrlEncoded
    @POST("/android/signUp")
    suspend fun signUp(
        @Field("userName") userName : String,
        @Field("userId") userId : String,
        @Field("password") password :String
        ) : String

    @GET("/android/signIn")
    suspend fun signIn(
        @Query("userId") userId : String,
        @Query("password") password :String
    ) : String
}