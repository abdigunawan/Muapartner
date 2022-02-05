package com.abdigunawan.muapartner.utils

import android.widget.TextView
import com.abdigunawan.muapartner.model.response.login.LoginResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun TextView.formatPrice(value : String) {
        this.text = getCurrendIDR(java.lang.Double.parseDouble(value))
    }
    fun getCurrendIDR(price : Double): String{
        val format = DecimalFormat("#,###,###")
        return "Rp "+format.format(price).replace(",".toRegex(),".")
    }

    fun getDefaultGson() : Gson? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                val format = SimpleDateFormat("", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null) {
                    JsonPrimitive(format.format(src))
                }else {
                    null
                }
            }).create()
    }

    fun ResponseBody.parseErrorBody(): LoginResponse<*>? {
        val gson = Gson()
        val adapter = gson.getAdapter(LoginResponse::class.java)
        try {
            return adapter.fromJson(string())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun Throwable.  getErrorBodyMessage(): String {
        return if (this is HttpException) {
            val errorCode = this.code()
            if (errorCode == 401) {
                val errorBody = this.response()?.errorBody()!!.parseErrorBody()
                if (errorBody?.message == "Masukkan data dengan lengkap") {
                    "Periksa Kembali data anda"
                } else {
                    errorBody?.message.toString()
                }

            } else if (errorCode == 503) {
                "Error Server"
            } else {
                val parseErrorBody = this.response()?.errorBody()!!.parseErrorBody()
                if (parseErrorBody?.message == null) {
                    "Permintaan anda belum berhasil di proses. Silakan coba kembali"
                } else {
                    parseErrorBody?.message.toString()
                }
            }

        } else if (this is ConnectException || this is UnknownHostException) {
            "Maaf Anda sedang Offline. Silakan coba kembali"

        } else {
            return if (this.message == null)
                "Permintaan anda belum berhasil di proses. Silakan coba kembali"
            else if (this.message.equals(""))
                ""
            else
                this.message!!

        }
    }
}