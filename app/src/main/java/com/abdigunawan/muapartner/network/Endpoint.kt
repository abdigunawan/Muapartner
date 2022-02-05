package com.abdigunawan.muapartner.network

import com.abdigunawan.muapartner.model.response.login.LoginResponse
import com.abdigunawan.muapartner.model.response.login.X0
import com.abdigunawan.muapartner.model.response.profile.paket.PaketMuaResponse
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email")email:String,
              @Field("password")password:String) : Observable<LoginResponse<X0>>

    @Multipart
    @POST("registermua")
    fun register(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("no_hp") no_hp: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("no_rumah") no_rumah: RequestBody,
        @Part("kota") kota: RequestBody,
        @Part gambar: MultipartBody.Part,
        @Part upload_sertifikat: MultipartBody.Part,
        @Part("roles") roles: RequestBody?
    ): Observable<LoginResponse<X0>>

    @Multipart
    @POST("produk/add")
    fun addproduk (
        @Part("nama_paket") nama_paket: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("produk") produk: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part foto: MultipartBody.Part
    ) : Observable<PaketMuaResponse>
}