package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.AturPaketModel
import com.abdigunawan.muapartner.model.response.profile.paket.Produk
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_paket_vertical.view.*

class PaketAdapter(
    private val listData: List<Produk>,
    private val itemAdapterCallBack: ItemAdapterCallback,
) : RecyclerView.Adapter<PaketAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_paket_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Produk, itemAdapterCallBack: ItemAdapterCallback) {
            val profilMua = BuildConfig.BASE_URL+"assets/img/mua/paket/" + data.foto
            itemView.apply {
                tvJudulPaket.text = data.namaPaket
                tvHarga.formatPrice(data.harga.toString())
                Glide.with(context)
                    .load(profilMua)
                    .into(ivFotoPaket)
                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: Produk)
    }
}