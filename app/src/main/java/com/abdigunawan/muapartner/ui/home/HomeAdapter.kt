package com.abdigunawan.muapartner.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.home.Transaksiuser
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pesananmasuk.view.*
import java.text.SimpleDateFormat

class HomeAdapter (

    private val listData: List<Transaksiuser>,
    private val itemAdapterCallBack: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pesananmasuk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Transaksiuser, itemAdapterCallBack: ItemAdapterCallback) {
            itemView.apply {

                val formatDate = SimpleDateFormat("dd MMMM YYYY")
                tvPelanggan.text = data.user.name
                tvPaketDipesan.text = data.transaksiuserx.namaPaket
                tvAlamatKetemu.text = data.user.alamat
                tvTanggalPesanan.text = formatDate.format(data.tanggalTransaksi)
                if (!data.transaksiuserx.foto.isNullOrEmpty()) {
                    val profilMua = BuildConfig.BASE_URL + "assets/img/mua/paket/" + data.transaksiuserx.foto
                    Glide.with(context)
                        .load(profilMua)
                        .into(ivPaketMua)
                }

                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: Transaksiuser)
    }
}