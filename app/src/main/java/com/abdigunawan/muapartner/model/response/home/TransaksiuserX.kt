package com.abdigunawan.muapartner.model.response.home


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransaksiuserX(
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("deskripsi")
    val deskripsi: String,
    @Expose
    @SerializedName("foto")
    val foto: String,
    @Expose
    @SerializedName("harga")
    val harga: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_user")
    val idUser: Int,
    @Expose
    @SerializedName("nama_paket")
    val namaPaket: String,
    @Expose
    @SerializedName("produk")
    val produk: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String
)