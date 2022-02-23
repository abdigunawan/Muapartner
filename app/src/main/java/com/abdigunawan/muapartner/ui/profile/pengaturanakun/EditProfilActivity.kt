package com.abdigunawan.muapartner.ui.profile.pengaturanakun

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.abdigunawan.muapartner.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditProfilActivity : AppCompatActivity() {

    lateinit var kota : Spinner
    var fotoProfil: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        customTolbar()
        initKota()
        initListener()

    }

    private fun customTolbar() {
        toolbar.title = "Edit Profil"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initKota() {
        kota = findViewById(R.id.spinnerKota) as Spinner
        val isikota = arrayOf("Makassar","Maros","Gowa","Bone")
        kota.adapter = ArrayAdapter<String>(this,R.layout.layout_spinner, isikota)
        kota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var kotaTerpilih = isikota.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initListener() {
        ivFotoProfil.setOnClickListener {
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
            fotoProfil = data?.data

            Glide.with(this)
                .load(fotoProfil)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFotoProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Pilih Foto Dibatallan", Toast.LENGTH_SHORT).show()
        }
    }

}