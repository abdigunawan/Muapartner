package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AddPaketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_paket)
        initToolbar()
    }

    private fun initToolbar() {
        val getTitle = intent.getStringExtra("title_request")
        toolbar.title = getTitle.toString()
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}