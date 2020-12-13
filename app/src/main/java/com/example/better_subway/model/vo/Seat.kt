package com.example.better_subway.model.vo

import com.google.gson.annotations.SerializedName

data class Seat(
    @SerializedName("TRAIN_NUM") var trainNum: Int,
    @SerializedName("BLOCK_NUM") var blockNum: String,
    @SerializedName("S1") var s1: Int,
    @SerializedName("S2") var s2: Int,
    @SerializedName("S3") var s3: Int,
    @SerializedName("S4") var s4: Int,
    @SerializedName("S5") var s5: Int,
    @SerializedName("S6") var s6: Int,
    @SerializedName("S7") var s7: Int,
    @SerializedName("S8") var s8: Int,
    @SerializedName("S9") var s9: Int,
    @SerializedName("S10") var s10: Int,
    @SerializedName("S11") var s11: Int,
    @SerializedName("S12") var s12: Int
)