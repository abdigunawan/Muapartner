package com.abdigunawan.muapartner.model.response.testimoni


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TestimoniX(
    @Expose
    @SerializedName("catatan")
    val catatan: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_transaksiuser")
    val idTransaksiuser: Int,
    @Expose
    @SerializedName("id_user")
    val idUser: Int,
    @Expose
    @SerializedName("jam_ketemu")
    val jamKetemu: String,
    @Expose
    @SerializedName("lokasi")
    val lokasi: String,
    @Expose
    @SerializedName("tanggal_ketemu")
    val tanggalKetemu: String,
    @Expose
    @SerializedName("tanggal_konfirmasi")
    val tanggalKonfirmasi: String
)