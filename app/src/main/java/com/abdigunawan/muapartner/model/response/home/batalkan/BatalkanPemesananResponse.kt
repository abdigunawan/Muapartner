package com.abdigunawan.muapartner.model.response.home.batalkan


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BatalkanPemesananResponse(
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