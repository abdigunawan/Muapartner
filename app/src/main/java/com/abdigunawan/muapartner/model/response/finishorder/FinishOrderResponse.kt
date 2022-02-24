package com.abdigunawan.muapartner.model.response.finishorder


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FinishOrderResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("transaksi")
    val transaksi: Transaksi,
    @Expose
    @SerializedName("0")
    val x0: Int
)