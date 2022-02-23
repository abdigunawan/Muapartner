package com.abdigunawan.muapartner.model.response.home.konfirmasi


import com.google.gson.annotations.SerializedName

data class Konfirmasitransaksi(
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_transaksiuser")
    val idTransaksiuser: Int,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("jam_ketemu")
    val jamKetemu: String,
    @SerializedName("lokasi")
    val lokasi: String,
    @SerializedName("tanggal_ketemu")
    val tanggalKetemu: String
)