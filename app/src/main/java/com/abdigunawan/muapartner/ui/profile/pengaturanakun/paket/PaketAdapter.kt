package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.AturPaketModel
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_paket_vertical.view.*

class PaketAdapter(
    private val listData: List<AturPaketModel>,
    private val itemAdapterCallBack: ItemAdapterCallback,
) : RecyclerView.Adapter<PaketAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_paket_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaketAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: AturPaketModel, itemAdapterCallBack: ItemAdapterCallback) {
            itemView.apply {
                tvJudulPaket.text = data.title
                tvHarga.formatPrice(data.price)

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: AturPaketModel)
    }
}