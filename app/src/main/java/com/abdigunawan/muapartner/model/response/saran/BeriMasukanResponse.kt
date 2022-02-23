package com.abdigunawan.muapartner.model.response.saran


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BeriMasukanResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("saran")
    val saran: Saran,
    @Expose
    @SerializedName("0")
    val x0: Int
)