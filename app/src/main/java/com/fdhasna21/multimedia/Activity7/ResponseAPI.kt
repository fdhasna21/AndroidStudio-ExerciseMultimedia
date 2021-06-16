package com.fdhasna21.multimedia.Activity7

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseAPI(
    @SerializedName("coord") val coord : Coord


) {
    @Parcelize
    data class Coord (
        @SerializedName("lat") val lat : Float,
        @SerializedName("lon") val long : Float
        ) : Parcelable
}
