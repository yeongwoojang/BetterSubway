package com.example.better_subway.model.vo

import com.google.gson.annotations.SerializedName

data class StationInfo(
    @SerializedName("code") var code : Int,
    @SerializedName("jsonArray") var jsonArray : List<Station>
)