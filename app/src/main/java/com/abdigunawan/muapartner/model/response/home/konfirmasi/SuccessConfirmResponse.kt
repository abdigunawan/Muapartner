package com.abdigunawan.muapartner.model.response.home.konfirmasi


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SuccessConfirmResponse(
    @Expose
    @SerializedName("konfirmasitransaksi")
    val konfirmasitransaksi: Konfirmasitransaksi,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("0")
    val x0: Int
)