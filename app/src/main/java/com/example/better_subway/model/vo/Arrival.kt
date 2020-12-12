package com.example.better_subway.model.vo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.sql.Timestamp

data class Arrival(
    @SerializedName("S_CODE") var sCode : Int,
    @SerializedName("STATION") var station : String,
    @SerializedName("DS_CODE") var dSCode : Int,
    @SerializedName("DIRECTION") var direction : String,
    @SerializedName("ARRIVAL_TIME") var arrivalTime : String,
    @SerializedName("TRAIN_NUM") var trainNum : Int,
    @SerializedName("PATH") var path : String
) : Parcelable{
    constructor(parcel : Parcel): this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(sCode)
        parcel?.writeString(station)
        parcel?.writeInt(dSCode)
        parcel?.writeString(direction)
        parcel?.writeString(arrivalTime)
        parcel?.writeInt(trainNum)
        parcel?.writeString(path)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Arrival>{
        override fun createFromParcel(source: Parcel): Arrival {
            return Arrival(source)
        }

        override fun newArray(size: Int): Array<Arrival?> {
            return arrayOfNulls(size)
        }
    }
}

