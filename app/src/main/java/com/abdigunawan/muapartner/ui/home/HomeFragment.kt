package com.abdigunawan.muapartner.ui.home

import android.app.Dialog
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
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.HomeModel
import com.abdigunawan.muapartner.model.response.home.HomeResponse
import com.abdigunawan.muapartner.model.response.home.Transaksiuser
import com.abdigunawan.muapartner.model.response.login.User
import com.abdigunawan.muapartner.ui.home.detail.DetailBookingActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvMuaLogin
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var adapter : HomeAdapter? = null
    var progressDialog: Dialog? = null
    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ucapanMua()
        initUser()
        initView()
        presenter = HomePresenter(this)
        presenter.getHome()
    }

    override fun onResume() {
        super.onResume()
        presenter = HomePresenter(this)
        presenter.getHome()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
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

    override fun onClick(v: View, data: Transaksiuser) {
        val detail = Intent(activity, DetailBookingActivity::class.java).putExtra("detailtransaksi", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        adapter = HomeAdapter(homeResponse.transaksiuser, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcPesananMasuk.layoutManager = layoutManager
        rcPesananMasuk.adapter = adapter

        if (homeResponse.transaksiuser.isNullOrEmpty()) {
            ivKosong.visibility = View.VISIBLE
            tvKosong.visibility = View.VISIBLE
            textView4.visibility = View.GONE
        } else {
            textView4.visibility = View.VISIBLE
            ivKosong.visibility = View.GONE
            tvKosong.visibility = View.GONE
        }
    }

    override fun onHomeFailed(message: String) {
        SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("OPSSSS!!!")
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