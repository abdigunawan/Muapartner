package com.abdigunawan.muapartner.model.response.riwayatorder


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RiwayatOrderResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("transaksiuser")
    val transaksiuser: List<Transaksiuser>
)