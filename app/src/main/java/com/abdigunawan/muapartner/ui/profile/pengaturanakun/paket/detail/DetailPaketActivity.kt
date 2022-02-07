package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.profile.paket.Produk
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.edit.EditPaketActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.edit.EditPaketPresenter
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_paket.*

class DetailPaketActivity : AppCompatActivity(),DeletePaketContract.View {

    private val produk by lazy { intent.getSerializableExtra("produk") as Produk }
    lateinit var presenter : DeletePaketPresenter
    var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)
        initView()
        initListener()
        presenter = DeletePaketPresenter(this)

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

        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initListener() {

        btnEditPaket.setOnClickListener {
            val edit = Intent(this, EditPaketActivity::class.java).putExtra("produk", produk)
            startActivity(edit)
            finish()
        }

        btnDelete.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Hapus Paket")
                .setContentText("Kamu Yakin Ingin Hapus Paket?")
                .setCancelButton("Batal", SweetAlertDialog.OnSweetClickListener {
                    it.dismissWithAnimation()
                })
                .setConfirmButton("Yakin", SweetAlertDialog.OnSweetClickListener {
                    presenter.deletePaket(produk.id.toString())
                })
                .show()
        }
    }

    override fun onDeletePaketSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDeletePaketFailed(message: String) {
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