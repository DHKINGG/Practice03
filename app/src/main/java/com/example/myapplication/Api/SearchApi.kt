package com.example.myapplication.Api

import com.example.myapplication.Activity.ApiUrlActivity
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalModel
import com.example.myapplication.Model.ResultCode
import com.example.myapplication.Model.SearchModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("/3660000/AnimalHospitalListService/getAnimalHospitalList") // 기본 베이스 url 뒤에 들어가는 변경 사항들
    fun getSearchApi(
        @Query(value = "serviceKey", encoded = true) serviceKey: String,
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("bplc_nm") bplc_nm: String
    ): Call<ResultCode>

    companion object {
        fun create() : SearchApi {
            val gson: Gson = GsonBuilder()
                .setLenient()
                .create()
            return  Retrofit.Builder()
                .baseUrl(ApiUrlActivity.searchBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SearchApi::class.java)
        }
    }
}