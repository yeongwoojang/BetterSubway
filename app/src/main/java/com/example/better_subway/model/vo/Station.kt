package com.example.better_subway.model.vo

import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("S_CODE") var sCode : String,
    @SerializedName("S_NAME") var  sName : String,
    @SerializedName("S_ENAME") var sEname : String,
    @SerializedName("LINE") var line : String,
    @SerializedName("CODE") var code : String
    )