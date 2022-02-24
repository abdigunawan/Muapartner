package com.abdigunawan.muapartner.ui.order.pastorders

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.riwayatorder.RiwayatOrderResponse
import com.abdigunawan.muapartner.model.response.riwayatorder.Transaksiuser
import com.abdigunawan.muapartner.ui.order.detailsorders.DetailOrderActivity
import kotlinx.android.synthetic.main.fragment_pastorders.*
import java.util.ArrayList

class PastordersFragment : Fragment(), PastordersAdapter.ItemAdapterCallback, pasOrderContract.View {
    
    private var adapter: PastordersAdapter? = null
    var progressDialog: Dialog? = null
    private lateinit var presenter: pastOrderPresenter
    val pastOrderList : ArrayList<Transaksiuser> = ArrayList()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pastorders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initView()
        presenter = pastOrderPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getTransaction()
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

    override fun onClick(v: View?, data: Transaksiuser) {
        val detail = Intent(activity, DetailOrderActivity::class.java).putExtra("detailtransaksi", data)
        startActivity(detail)
    }

    override fun onTransactionSuccess(riwayatOrderResponse: RiwayatOrderResponse) {

        pastOrderList.clear()

        riwayatOrderResponse.transaksiuser.forEach {
            if (it.status.equals("DIBATALKAN") || it.status.equals("SELESAI")) {
                pastOrderList.add(it)
//                pastOrderList.sortByDescending { data -> data.tanggalTransaksi }
            }
        }

        adapter = PastordersAdapter(pastOrderList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        if (pastOrderList.isNullOrEmpty()) {
            rcList.visibility = View.GONE
            ll_empty.visibility = View.VISIBLE
        } else {
            rcList.visibility = View.VISIBLE
            ll_empty.visibility = View.GONE
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}