package com.abdigunawan.muapartner.ui.testimoni

import android.app.Dialog
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
import com.abdigunawan.muapartner.model.response.testimoni.Testimoni
import com.abdigunawan.muapartner.model.response.testimoni.TestimoniResponse
import kotlinx.android.synthetic.main.fragment_testimoni.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class TestimoniFragment : Fragment(),TestimoniAdapter.ItemAdapterCallback, TestimoniContract.View {

    private var adapter: TestimoniAdapter? = null
    var progressDialog: Dialog? = null
    private lateinit var presenter: TestimoniPresenter

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


        presenter = TestimoniPresenter(this)
        initToolbar()
        presenter.getTestimoni()
    }

    fun initToolbar () {
        toolbar.title = "Testimoni"
        toolbar.subtitle = "Testimoni yang diberikan pelanggan"
    }

    override fun onClick(v: View, data: Testimoni) {
    }

    override fun onTestimoniSuccess(testimoniResponse: TestimoniResponse) {
        var adapter = TestimoniAdapter(testimoniResponse.testimoni.asReversed() , this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcTestimoni.layoutManager = layoutManager
        rcTestimoni.adapter = adapter

        if (testimoniResponse.testimoni.isNullOrEmpty()) {
            rcTestimoni.visibility = View.GONE
            layoutTestimoniKosong.visibility = View.VISIBLE
        } else {
            rcTestimoni.visibility = View.VISIBLE
            layoutTestimoniKosong.visibility = View.GONE
        }
    }


    override fun onTestimoniFailed(message: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}