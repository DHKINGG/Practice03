package com.example.myapplication.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchModel(
    @SerializedName("bplc_nm") var hospitalName : String,
    @SerializedName("rdnmadr") var hospitalAddress : String
) : Serializable