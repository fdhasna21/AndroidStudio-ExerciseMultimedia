package com.fdhasna21.multimedia.Activity7

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseAPI(
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : ArrayList<Weather>,
    @SerializedName("main") val detailInfo:Info,
    @SerializedName("sys") val detailSys : LocationSystem,
    @SerializedName("timezone") val timezone : Long
) {
    @Parcelize
    data class Coord (
        @SerializedName("lat") val lat : Float,
        @SerializedName("lon") val long : Float
        ) : Parcelable

    @Parcelize
    data class Weather (
        @SerializedName("main") val main:String,
        @SerializedName("icon") val icon:String
    ) : Parcelable

    @Parcelize
    data class Info (
        @SerializedName("temp") val currentTemp : Float,
        @SerializedName("temp_min") val minTemp : Float,
        @SerializedName("temp_max") val maxTemp : Float,
        @SerializedName("pressure") val pressure : Float,
        @SerializedName("humidity") val humidity : Float
    ) : Parcelable

    @Parcelize
    data class LocationSystem (
        @SerializedName("sunrise") val sunrise : Long,
        @SerializedName("sunset") val sunset : Long
    ) : Parcelable
}
