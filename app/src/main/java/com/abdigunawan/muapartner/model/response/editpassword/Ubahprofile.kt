package com.abdigunawan.muapartner.model.response.editpassword


import com.google.gson.annotations.SerializedName

data class Ubahprofile(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kota")
    val kota: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("no_rumah")
    val noRumah: String,
    @SerializedName("roles")
    val roles: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("upload_sertifikat")
    val uploadSertifikat: String
)