package com.abdigunawan.muapartner.ui.testimoni

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.MuaTestimoniModel
import com.abdigunawan.muapartner.model.response.testimoni.Testimoni
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_inprogress.view.*
import kotlinx.android.synthetic.main.item_testimoni_horizontal.view.*
import java.text.SimpleDateFormat

class TestimoniAdapter (
    private val listData: List<Testimoni>,
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
        fun bind(data: Testimoni, itemAdapterCallBack: ItemAdapterCallback) {
            itemView.apply {
                tvPelanggan.text = data.user.name
                tvKomentar.text = data.catatan
                var formatDate = SimpleDateFormat("dd MMM yyyy")
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                val date = simpleDateFormat.parse(data.tanggalTestimoni)
                tvtanggalTestimoni.text = formatDate.format(date)

                if (!data.gambar.isNullOrEmpty()) {
                    val testimoniMua = BuildConfig.BASE_URL + "assets/img/mua/testimoni/" + data.gambar
                    Glide.with(context)
                        .load(testimoniMua)
                        .into(ivTestimoni)
                }

                itemView.setOnClickListener { itemAdapterCallBack.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: Testimoni)
    }
}