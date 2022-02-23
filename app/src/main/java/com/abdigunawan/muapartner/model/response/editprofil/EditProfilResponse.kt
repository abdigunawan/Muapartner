package com.abdigunawan.muapartner.model.response.editprofil


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EditProfilResponse<T>(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("ubahprofile")
    val ubahprofile: Ubahprofile,
    @Expose
    @SerializedName("0")
    val x0: Int
)