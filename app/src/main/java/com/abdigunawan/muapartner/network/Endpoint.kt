package com.abdigunawan.muapartner.network

import com.abdigunawan.muapartner.model.response.login.LoginResponse
import com.abdigunawan.muapartner.model.response.login.X0
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email")email:String,
              @Field("password")password:String) : Observable<LoginResponse<X0>>

    @Multipart
    @POST("registermua")
    fun register(@Field("name") name: String,
                 @Field("email") email: String,
                 @Field("password") password: String,
                 @Field("no_hp") no_hp: String,
                 @Field("alamat") alamat: String,
                 @Field("no_rumah") no_rumah: String,
                 @Field("kota") kota: String,
                 @Part profileImage: MultipartBody.Part,
                 @Part sertifikatImage: MultipartBody.Part,
                 @Field("roles") roles: String): Observable<LoginResponse<Any?>>

}