package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.Navigation
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.profile.paket.GetPaketResponse
import com.abdigunawan.muapartner.model.response.profile.paket.Produk
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add.AddPaketActivity
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_paket.*
import kotlinx.android.synthetic.main.item_paket_vertical.view.*

class DetailPaketActivity : AppCompatActivity() {

    private val produk by lazy { intent.getSerializableExtra("produk") as Produk }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)
        initView()

    }

    private fun initView() {
        tvNamaPaket.text = produk.namaPaket
        tvDesc.text = produk.deskripsi
        tvTotal.formatPrice(produk.harga.toString())
        tvIngredients.text = produk.produk
        val profilMua = BuildConfig.BASE_URL+"assets/img/mua/paket/" + produk.foto
        Glide.with(this)
            .load(profilMua)
            .into(ivPaketMua)
    }

}