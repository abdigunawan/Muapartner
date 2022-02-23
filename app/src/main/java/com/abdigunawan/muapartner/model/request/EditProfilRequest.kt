package com.abdigunawan.muapartner.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import okhttp3.RequestBody

@Parcelize
data class EditProfilRequest(
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("no_hp")
    var no_hp: String,
    @Expose
    @SerializedName("alamat")
    var alamat: String,
    @Expose
    @SerializedName("no_rumah")
    var no_rumah: String,
    @Expose
    @SerializedName("kota")
    var kota: String,
    @Expose
    @SerializedName("roles")
    var roles: String?,
    @Expose
    @SerializedName("gambar")
    var gambar: Uri?=null
) : Parcelable