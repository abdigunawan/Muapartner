package com.abdigunawan.muapartner.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse<T>(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("0")
    val x0: X0,
    @Expose
    @SerializedName("1")
    val x1: Int
)