package com.abdigunawan.muapartner.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.ProfilPaketModel
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_paket_horizontal.view.*

class ProfilAdapter (
    private val listData: List<ProfilPaketModel>,
    private val itemAdapterCallBack: ItemAdapterCallback,
) : RecyclerView.Adapter<ProfilAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_paket_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ProfilPaketModel, itemAdapterCallBack: ItemAdapterCallback) {
            itemView.apply {
                tvNamaPaket.text = data.title
                tvHarga.formatPrice(data.price)

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: ProfilPaketModel)
    }
}