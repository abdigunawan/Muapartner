package com.abdigunawan.muapartner.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.databinding.FragmentOrderBinding
import com.abdigunawan.muapartner.model.response.riwayatorder.RiwayatOrderResponse
import com.abdigunawan.muapartner.model.response.riwayatorder.Transaksiuser
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        val sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initView() {
        include_toolbar.toolbar.title = "Riwayat Booking"
        include_toolbar.toolbar.subtitle = "Semua Riwayat Booking Kamu"
    }


}