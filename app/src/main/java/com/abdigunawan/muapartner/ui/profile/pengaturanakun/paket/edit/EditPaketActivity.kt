package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.edit

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.request.AddPaketRequest
import com.abdigunawan.muapartner.model.request.EditPaketRequest
import com.abdigunawan.muapartner.model.response.profile.paket.Produk
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail.DetailPaketActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_edit_paket.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditPaketActivity : AppCompatActivity(),EditPaketContract.View {

    private val produk by lazy { intent.getSerializableExtra("produk") as Produk }
    lateinit var editPaketRequest: EditPaketRequest
    lateinit var presenter : EditPaketPresenter
    var progressDialog: Dialog? = null
    var fotopaket: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_paket)
        presenter = EditPaketPresenter(this)
        initToolbar()
        initListener()
        initView()
    }

    private fun initToolbar() {
        toolbar.title = "Edit Paket"
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
            var produkmua = etProdukMakeup.text.toString()
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
            } else if (produkmua.isNullOrEmpty()) {
                etProdukMakeup.error = "Masukkan Produk yang kamu gunakan"
                etProdukMakeup.requestFocus()
            } else {
                var data = EditPaketRequest(
                    produk.id.toString(),
                    namapaket,
                    deskripsi,
                    produkmua,
                    harga,
                    fotopaket
                )

                presenter.editPaket(data,it)
            }
        }

    }

    private fun initView(){
        etNamaPaket.setText(produk.namaPaket)
        etHarga.setText(produk.harga.toString())
        etDeskripsi.setText(produk.deskripsi)
        etProdukMakeup.setText(produk.produk)
        val profilMua = BuildConfig.BASE_URL+"assets/img/mua/paket/" + produk.foto
        Glide.with(this)
            .load(profilMua)
            .apply(RequestOptions.centerCropTransform())
            .into(ivPaketFoto)

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

    override fun onEditPaketSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()

    }

    override fun onEditPaketFailed(message: String) {
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