package com.abdigunawan.muapartner.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import okhttp3.RequestBody

data class AddPaketRequest(
    @Expose
    @SerializedName("nama_paket")
    var nama_paket: String,
    @Expose
    @SerializedName("deskripsi")
    var deskripsi: String,
    @Expose
    @SerializedName("produk")
    var produk: String,
    @Expose
    @SerializedName("harga")
    var harga: String,
    @Expose
    @SerializedName("foto")
    var foto: Uri?=null
)