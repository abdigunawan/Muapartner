package com.abdigunawan.muapartner.ui.testimoni

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.MuaTestimoniModel
import kotlinx.android.synthetic.main.item_testimoni_horizontal.view.*

class TestimoniAdapter (
    private val listData: List<MuaTestimoniModel>,
    private val itemAdapterCallBack: ItemAdapterCallback,
) : RecyclerView.Adapter<TestimoniAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestimoniAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_testimoni_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestimoniAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MuaTestimoniModel, itemAdapterCallBack: ItemAdapterCallback) {
            itemView.apply {
                tvPelanggan.text = data.pelanggan
                tvKomentar.text = data.komentar
                tvtanggalTestimoni.text = data.tanggal

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: MuaTestimoniModel)
    }
}