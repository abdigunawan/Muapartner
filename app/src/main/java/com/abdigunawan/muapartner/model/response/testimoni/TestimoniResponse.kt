package com.abdigunawan.muapartner.model.response.testimoni


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TestimoniResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("testimoni")
    val testimoni: List<Testimoni>
)