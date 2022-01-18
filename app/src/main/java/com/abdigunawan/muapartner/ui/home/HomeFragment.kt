package com.abdigunawan.muapartner.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.HomeModel
import kotlinx.android.synthetic.main.fragment_home.*

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

    override fun onClick(v: View, data: HomeModel) {
        Toast.makeText(context, "Ini menu yang kamu klik "+ data.pelanggan, Toast.LENGTH_SHORT).show()
    }

}