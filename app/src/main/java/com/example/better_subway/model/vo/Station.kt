package com.example.better_subway.model.vo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("S_CODE") @Expose var sCode : String,
    @SerializedName("S_NAME") @Expose var  sName : String,
    @SerializedName("S_ENAME") @Expose var sEname : String,
    @SerializedName("LINE") @Expose var line : String,
    @SerializedName("CODE") @Expose var code : String
    )