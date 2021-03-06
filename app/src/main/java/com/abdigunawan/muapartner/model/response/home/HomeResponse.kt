package com.abdigunawan.muapartner.model.response.home


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("transaksiuser")
    val transaksiuser: List<Transaksiuser>
) : Serializable