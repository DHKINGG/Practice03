package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class LocalDataModel (
    @SerializedName("row") var row : ArrayList<HomeBookModel>
)