package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.AturPaketModel
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail.DetailPaketActivity
import kotlinx.android.synthetic.main.activity_paket.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PaketActivity : AppCompatActivity(),PaketAdapter.ItemAdapterCallback {

    private var paketList : ArrayList<AturPaketModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket)

        initToolbar()
        initDataDummy()
        var adapter = PaketAdapter(paketList, this)
        var layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        rcPaket.layoutManager = layoutManager
        rcPaket.adapter = adapter

    }

    private fun initToolbar() {
        toolbar.title = "Atur Paketmu"
        toolbar.subtitle = "Atur Paketmu Sesukamu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun initDataDummy(){
        paketList = ArrayList()
        paketList.add(AturPaketModel("Paket Wisuda","289000",""))
        paketList.add(AturPaketModel("Paket Pernikahan","350000",""))
        paketList.add(AturPaketModel("Paket Tamu Pernikahan","300000",""))
        paketList.add(AturPaketModel("Paket Wisuda","289000",""))
        paketList.add(AturPaketModel("Paket Pernikahan","350000",""))
        paketList.add(AturPaketModel("Paket Tamu Pernikahan","300000",""))
    }

    override fun onClick(v: View, data: AturPaketModel) {
        val detailpaket = Intent(this, DetailPaketActivity::class.java)
        detailpaket.putExtra("page_request", 2)
        startActivity(detailpaket)
    }
}