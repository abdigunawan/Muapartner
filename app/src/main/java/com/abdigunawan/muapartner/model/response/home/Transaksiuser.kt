package com.abdigunawan.muapartner.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaksiuser(
    @Expose
    @SerializedName("catatan")
    val catatan: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("id_paket")
    val idPaket: Int,
    @Expose
    @SerializedName("id_tukang")
    val idTukang: Int,
    @Expose
    @SerializedName("id_user")
    val idUser: Int,
    @Expose
    @SerializedName("jam_acara")
    val jamAcara: String,
    @Expose
    @SerializedName("jumlah")
    val jumlah: String,
    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("tanggal_acara")
    val tanggalAcara: String,
    @Expose
    @SerializedName("tanggal_transaksi")
    val tanggalTransaksi: String,
    @Expose
    @SerializedName("total_harga")
    val totalHarga: String,
    @Expose
    @SerializedName("transaksiuser")
    val transaksiuserx: TransaksiuserX,
    @Expose
    @SerializedName("user")
    val user: User
) : Parcelable