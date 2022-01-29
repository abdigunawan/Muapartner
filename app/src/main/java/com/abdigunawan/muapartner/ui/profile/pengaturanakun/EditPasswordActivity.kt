package com.abdigunawan.muapartner.ui.profile.pengaturanakun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_password)
        customTolbar()
    }

    private fun customTolbar() {
        toolbar.title = "Edit Password"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}