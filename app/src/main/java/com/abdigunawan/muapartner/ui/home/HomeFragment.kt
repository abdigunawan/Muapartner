package com.abdigunawan.muapartner.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.HomeModel
import com.abdigunawan.muapartner.model.response.login.User
import com.abdigunawan.muapartner.ui.home.detail.DetailBookingActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvMuaLogin
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),HomeAdapter.ItemAdapterCallback {

    private var pesananList : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        ucapanMua()
        initUser()
        var adapter = HomeAdapter(pesananList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcPesananMasuk.layoutManager = layoutManager
        rcPesananMasuk.adapter = adapter
    }

    fun initDataDummy() {
        pesananList = ArrayList()
        pesananList.add(HomeModel("Natalya Tolla", "Paket Wisuda", "", "Daya, Paccerakkang", "9 Nov, 13.00-14.00"))
        pesananList.add(HomeModel("Adella Dewi", "Paket Nikah", "", "Perintis Kemerdekaan 7", "10 Nov, 9.00-12.00"))
        pesananList.add(HomeModel("Nurul Fadillah", "Paket Nikah","", "Jalan Poros Bone Makassar","10 Nov, 13.00-14.00"))
        pesananList.add(HomeModel("Natalya Tolla", "Paket Wisuda", "", "Daya, Paccerakkang", "9 Nov, 13.00-14.00"))
        pesananList.add(HomeModel("Adella Dewi", "Paket Nikah", "", "Perintis Kemerdekaan 7","10 Nov, 9.00-12.00"))
        pesananList.add(HomeModel("Nurul Fadillah", "Paket Nikah" ,"", "Jalan Poros Bone Makassar", "10 Nov, 13.00-14.00"))

    }

    private fun initUser() {
        var user = MuaPartner.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.gambar.isNullOrEmpty()) {
            val profilMua = BuildConfig.BASE_URL+"assets/img/mua/" + userResponse.gambar
            Glide.with(requireActivity())
                .load(profilMua)
                .apply(RequestOptions.centerCropTransform())
                .into(ivMuaLogin)
        }

        tvMuaLogin.setText(userResponse.name)

    }

    private fun ucapanMua() {
        val calendar = Calendar.getInstance()
        val hour24hrs = calendar[Calendar.HOUR_OF_DAY]

        if (hour24hrs >= 5 && hour24hrs <= 10) {
            tvUcapan.setText("Selamat Pagi")
        }else if (hour24hrs >= 11 && hour24hrs <= 15) {
            tvUcapan.setText("Selamat Siang")
        }else if (hour24hrs >= 16 && hour24hrs <= 18) {
            tvUcapan.setText("Selamat Sore")
        }else {
            tvUcapan.setText("Selamat Malam")
        }
    }

    override fun onClick(v: View, data: HomeModel) {
        val detailmua = Intent(activity, DetailBookingActivity::class.java)
        startActivity(detailmua)
    }

}