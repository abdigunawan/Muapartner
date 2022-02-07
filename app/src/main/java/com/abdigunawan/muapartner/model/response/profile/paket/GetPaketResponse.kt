package com.abdigunawan.muapartner.model.response.profile.paket


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPaketResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("produk")
    val `produk`: List<Produk>,
    @Expose
    @SerializedName("0")
    val x0: Int
)