package com.abdigunawan.muapartner.ui.testimoni

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.dummy.MuaTestimoniModel
import kotlinx.android.synthetic.main.fragment_testimoni.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class TestimoniFragment : Fragment(),TestimoniAdapter.ItemAdapterCallback {

    private var pesananList : ArrayList<MuaTestimoniModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_testimoni, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        initToolbar()
        var adapter = TestimoniAdapter(pesananList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcTestimoni.layoutManager = layoutManager
        rcTestimoni.adapter = adapter
    }

    fun initToolbar () {
        toolbar.title = "Testimoni"
        toolbar.subtitle = "Testimoni yang diberikan pelanggan"
    }

    fun initDataDummy() {
        pesananList = ArrayList()
        pesananList.add(MuaTestimoniModel("Saripudding", "", "Uhh Suka Banget Hasilnya", "9 Nov, 13.00-14.00"))
        pesananList.add(MuaTestimoniModel("Rusdi", "", "hmm bagusnya", "9 Nov, 13.00-14.00"))
        pesananList.add(MuaTestimoniModel("Jamal", "", "Astaga Bagus sekali kak", "9 Nov, 13.00-14.00"))
        pesananList.add(MuaTestimoniModel("Asdar", "", "yatawwa bagus", "9 Nov, 13.00-14.00"))
        pesananList.add(MuaTestimoniModel("Ikran", "", "chuaksssss", "9 Nov, 13.00-14.00"))
        pesananList.add(MuaTestimoniModel("Abdi", "", "ngonggg", "9 Nov, 13.00-14.00"))


    }

    override fun onClick(v: View, data: MuaTestimoniModel) {
        Toast.makeText(context,""+data,Toast.LENGTH_SHORT).show()
    }

}