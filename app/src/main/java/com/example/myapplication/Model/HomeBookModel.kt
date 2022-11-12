package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class HomeBookModel (
    @SerializedName("BPLCNM") var hospitalName : String,
    @SerializedName("MEDEXTRITEMSCNNM") var treatmentSubject : String,
    @SerializedName("RDNWHLADDR") var hospitalAddress : String,
    @SerializedName("METRORGASSRNM") var hospitalType :String
    )