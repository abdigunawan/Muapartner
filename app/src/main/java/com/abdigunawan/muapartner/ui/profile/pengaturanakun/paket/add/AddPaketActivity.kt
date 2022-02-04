package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdigunawan.muapartner.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_paket.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AddPaketActivity : AppCompatActivity() {

    var fotopaket: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_paket)
        initToolbar()
        initListener()
    }

    private fun initToolbar() {
        val getTitle = intent.getStringExtra("title_request")
        toolbar.title = getTitle.toString()
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initListener() {

        ivPaketFoto.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            fotopaket = data?.data

            Glide.with(this)
                .load(fotopaket)
                .apply(RequestOptions.centerCropTransform())
                .into(ivPaketFoto)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Pilih Foto Dibatallan", Toast.LENGTH_SHORT).show()
        }
    }
}