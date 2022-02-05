package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.request.AddPaketRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_paket.*
import kotlinx.android.synthetic.main.activity_add_paket.btnSimpan
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.security.AccessController.getContext

class AddPaketActivity : AppCompatActivity(),AddPaketContract.View {

    lateinit var presenter : AddPaketPresenter
    var progressDialog: Dialog? = null
    var fotopaket: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_paket)
        presenter = AddPaketPresenter(this)
        initToolbar()
        initListener()
        initView()
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

        btnSimpan.setOnClickListener {

            var namapaket = etNamaPaket.text.toString()
            var deskripsi = etDeskripsi.text.toString()
            var produk = etProdukMakeup.text.toString()
            var harga = etHarga.text.toString()


            if (namapaket.isNullOrEmpty()) {
                etNamaPaket.error = "Masukkan Nama Paket"
                etNamaPaket.requestFocus()
            } else if (harga.isNullOrEmpty()) {
                etHarga.error = "Masukkan harga paketmu"
                etHarga.requestFocus()
            } else if (deskripsi.isNullOrEmpty()) {
                etDeskripsi.error = "Masukkan Deskripsinya"
                etDeskripsi.requestFocus()
            } else if (produk.isNullOrEmpty()) {
                etProdukMakeup.error = "Masukkan Produk yang kamu gunakan"
                etProdukMakeup.requestFocus()
            } else if (fotopaket == null) {
                Toast.makeText(this, "Pilih Foto Produk Dulu", Toast.LENGTH_SHORT).show()
            } else {
                var data = AddPaketRequest(
                    namapaket,
                    deskripsi,
                    produk,
                    harga,
                    fotopaket
                )

                presenter.addPaket(data,it)
            }
        }

    }

    private fun initView(){
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
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


    override fun onAddPaketSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onAddPaketFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("GAGAL")
            .setContentText(message)
            .show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}