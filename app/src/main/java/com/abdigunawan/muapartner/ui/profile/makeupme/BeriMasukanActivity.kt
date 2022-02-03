package com.abdigunawan.muapartner.ui.profile.makeupme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.activity_beri_masukan.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class BeriMasukanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beri_masukan)
        initTolbar()
        initListener()
    }

    private fun initTolbar(){
        toolbar.title = "Beri Masukan"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListener() {
        btnSimpanSaran.setOnClickListener {
            Toast.makeText(this, "Saran Anda : " + etCatatan.text.toString(), Toast.LENGTH_SHORT ).show()
        }
    }

}