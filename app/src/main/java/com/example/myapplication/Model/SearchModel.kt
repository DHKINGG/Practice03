package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("bplc_nm") var hospitalName : String,
    @SerializedName("rdnmadr") var hospitalAddress : String
)