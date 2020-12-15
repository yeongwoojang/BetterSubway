package com.example.better_subway.model.vo

import com.google.gson.annotations.SerializedName

data class BookMark(
    @SerializedName("USER_ID") var userId : String,
    @SerializedName("STATION") var station : String
)