package com.example.myapplication.Api

import com.example.myapplication.Activity.ApiUrlActivity
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalModel
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeBookApi {
    @GET("/{apiKey}/json/LOCALDATA_010101_GN/{startIndex}/{endIndex}")
    fun getHomeBookApi(
        @Path("apiKey") apiKey: String,
        @Path("startIndex") startIndex: String,
        @Path("endIndex") endIndex: String
    ): Call<HospitalModel>

    ///776b5459726877693536666d475149/json/LOCALDATA_010101_GN/1/100/
    companion object {
        fun create() : HomeBookApi {
            return  Retrofit.Builder()
                .baseUrl(ApiUrlActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HomeBookApi::class.java)
        }
    }
}