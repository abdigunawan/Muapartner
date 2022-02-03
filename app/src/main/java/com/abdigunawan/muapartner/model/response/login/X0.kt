package com.abdigunawan.muapartner.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class X0(
    @Expose
    @SerializedName("token")
    val token: String,
    @Expose
    @SerializedName("user")
    val user: User
)