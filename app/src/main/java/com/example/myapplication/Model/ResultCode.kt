package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class ResultCode (
//    @SerializedName("resultlist") var resultList : SearchModel
    @SerializedName("resultMsg") var resultList : String
    )