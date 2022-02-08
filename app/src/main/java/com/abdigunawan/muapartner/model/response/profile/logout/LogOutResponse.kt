package com.abdigunawan.muapartner.model.response.profile.logout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LogOutResponse(
    @Expose
    @SerializedName("message")
    val message: String
)