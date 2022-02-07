package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.AturPaketModel
import com.abdigunawan.muapartner.model.response.profile.paket.GetPaketResponse
import com.abdigunawan.muapartner.model.response.profile.paket.Produk
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add.AddPaketActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail.DetailPaketActivity
import kotlinx.android.synthetic.main.activity_paket.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PaketActivity : AppCompatActivity(),PaketAdapter.ItemAdapterCallback, PaketContract.View {

    private var adapter : PaketAdapter? = null
    var progressDialog: Dialog? = null
    private lateinit var presenter: PaketPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket)

        initView()
        initToolbar()
        initListener()
        presenter = PaketPresenter(this)
        presenter.getPaket()

    }

    override fun onResume() {
        super.onResume()
        presenter.getPaket()
    }

    override fun onStart() {
        super.onStart()
        presenter.getPaket()
    }

    override fun onPause() {
        super.onPause()
        presenter.getPaket()
    }

    private fun initView() {
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initToolbar() {
        toolbar.title = "Atur Paketmu"
        toolbar.subtitle = "Atur Paketmu Sesukamu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListener() {

        btnAdd.setOnClickListener {
            val add = Intent(this, AddPaketActivity::class.java)
            startActivity(add)
        }

    }

    override fun onClick(v: View, data: Produk) {
        val detail = Intent(this, DetailPaketActivity::class.java).putExtra("produk", data)
        startActivity(detail)
    }

    override fun onPaketSuccess(getPaketResponse: GetPaketResponse) {
        adapter = PaketAdapter(getPaketResponse.produk, this)
        var layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        rcPaket.layoutManager = layoutManager
        rcPaket.adapter = adapter

        if (getPaketResponse.produk.isNullOrEmpty()) {
            paketKosong.visibility = View.VISIBLE
        } else {
            paketKosong.visibility = View.GONE
        }
    }

    override fun onPaketFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("GAGAL MEMUAT PAKET")
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