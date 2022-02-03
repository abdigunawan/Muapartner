package com.abdigunawan.muapartner.ui.profile.pengaturanakun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditProfilActivity : AppCompatActivity() {

    lateinit var kota : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        customTolbar()
        initKota()

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

}