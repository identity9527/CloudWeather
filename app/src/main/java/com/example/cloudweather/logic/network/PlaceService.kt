package com.example.cloudweather.logic.network

import com.example.cloudweather.SunnyWeatherApplication
import com.example.cloudweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Build time：2020/6/20
 *Author：Tang
 *Des:
 */
interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}