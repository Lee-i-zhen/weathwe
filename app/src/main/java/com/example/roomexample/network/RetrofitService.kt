package com.example.roomexample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*
* retrieve weather data from: (example: Hualien city)
* https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=API_KEY&locationName=花蓮縣
*/

private const val API_URL = "https://api.openweathermap.org/"
const val API_KEY = "58bbc5d838240a026613a66c994cc15e"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(API_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface RetrofitService {
    @GET("F-C0032-001?Authorization=$API_KEY")
    suspend fun getAppData(@Query("locationName") location: String, apiKey: Any?): WeatherData
}

//global singleton object
object GetService {
    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}