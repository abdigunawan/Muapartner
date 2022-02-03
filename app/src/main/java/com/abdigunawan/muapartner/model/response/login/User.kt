package com.abdigunawan.muapartner.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("alamat")
    val alamat: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @Expose
    @SerializedName("gambar")
    val gambar: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("kota")
    val kota: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("no_hp")
    val noHp: String,
    @Expose
    @SerializedName("no_rumah")
    val noRumah: String,
    @Expose
    @SerializedName("roles")
    val roles: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String,
    @Expose
    @SerializedName("upload_sertifikat")
    val uploadSertifikat: Any
)