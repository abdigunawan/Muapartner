package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add.AddPaketActivity
import kotlinx.android.synthetic.main.activity_detail_paket.*

class DetailPaketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)
        initListener()
    }

    private fun initListener() {
        btnEditPaket.setOnClickListener {
            val edit = Intent(this, AddPaketActivity::class.java)
            edit.putExtra("title_request", "Edit Paket")
            startActivity(edit)
        }
    }
}