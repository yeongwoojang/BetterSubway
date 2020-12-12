package com.example.better_subway.model.vo

import com.google.gson.annotations.SerializedName

data class Seat(
    @SerializedName("TRAIN_NUM") var trainNum: Int,
    @SerializedName("BLOCK_NUM") var blockNum: String,
    @SerializedName("S1") var s1: Boolean,
    @SerializedName("S2") var s2: Boolean,
    @SerializedName("S3") var s3: Boolean,
    @SerializedName("S4") var s4: Boolean,
    @SerializedName("S5") var s5: Boolean,
    @SerializedName("S6") var s6: Boolean,
    @SerializedName("S7") var s7: Boolean,
    @SerializedName("S8") var s8: Boolean,
    @SerializedName("S9") var s9: Boolean,
    @SerializedName("S10") var s10: Boolean,
    @SerializedName("S11") var s11: Boolean,
    @SerializedName("S12") var s12: Boolean
)