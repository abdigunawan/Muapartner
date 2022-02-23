package com.abdigunawan.muapartner.network

import com.abdigunawan.muapartner.model.response.editpassword.EditPasswordResponse
import com.abdigunawan.muapartner.model.response.editprofil.EditProfilResponse
import com.abdigunawan.muapartner.model.response.editprofil.Ubahprofile
import com.abdigunawan.muapartner.model.response.home.HomeResponse
import com.abdigunawan.muapartner.model.response.home.konfirmasi.SuccessConfirmResponse
import com.abdigunawan.muapartner.model.response.login.LoginResponse
import com.abdigunawan.muapartner.model.response.login.X0
import com.abdigunawan.muapartner.model.response.profile.logout.LogOutResponse
import com.abdigunawan.muapartner.model.response.profile.paket.AddPaketResponse
import com.abdigunawan.muapartner.model.response.profile.paket.EditPaketResponse
import com.abdigunawan.muapartner.model.response.profile.paket.GetPaketResponse
import com.abdigunawan.muapartner.model.response.saran.BeriMasukanResponse
import io.reactivex.Observable
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
        @Part gambar: MultipartBody.Part?,
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
    ) : Observable<AddPaketResponse>

    @POST("logout")
    fun logout() : Observable<LogOutResponse>

    @Multipart
    @POST("produk/update/{id}")
    fun updateproduk (
        @Path(value = "id") id_paket: String,
        @Part("nama_paket") nama_paket: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("produk") produk: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part foto: MultipartBody.Part?
    ) : Observable<EditPaketResponse>

    @DELETE("produk/delete/{id}")
    fun deleteproduk (
        @Path(value = "id") id_paket: String,
    ) : Observable<EditPaketResponse>

    @GET("produk/get")
    fun getproduk() : Observable<GetPaketResponse>

    @GET("transaksi/show/")
    fun getHome() : Observable<HomeResponse>

    @FormUrlEncoded
    @POST("ubahpassword")
    fun editpassword(@Field("passwordlama")passwordlama:String,
                     @Field("passwordbaru")passwordbaru:String,
                     @Field("passwordkonfirmasi")passwordkonfirmasi:String) : Observable<EditPasswordResponse>

    @FormUrlEncoded
    @POST("saran")
    fun saran(@Field("id_user")id_user:String?,
              @Field("saran")saran:String) : Observable<BeriMasukanResponse>

    @Multipart
    @POST("ubahprofilemua/{id}")
    fun editprofil(
        @Path(value = "id") userid:String,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("no_hp") no_hp: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("no_rumah") no_rumah: RequestBody,
        @Part("kota") kota: RequestBody,
        @Part gambar: MultipartBody.Part?,
        @Part("roles") roles: RequestBody?
    ): Observable<EditProfilResponse<Ubahprofile>>

    @FormUrlEncoded
    @POST("konfirmasi/{id}")
    fun konfirmasiTransaksi(
        @Path(value = "id") paketId:String,
        @Field("tanggal_ketemu")tanggalketemu:String,
        @Field("jam_ketemu")jamketemu:String,
        @Field("lokasi")lokasi:String,
        @Field("catatan")catatan:String,
    ) : Observable<SuccessConfirmResponse>

}