package com.fdhasna21.multimedia.Activity7

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Activity7Interface {
    @GET("weather")
    fun getWeather(
        @Query("q") location:String,
        @Query("units") unit:String,
    ) : Call<ResponseAPI>
}