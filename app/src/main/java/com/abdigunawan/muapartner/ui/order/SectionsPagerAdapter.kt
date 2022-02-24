package com.abdigunawan.muapartner.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.abdigunawan.muapartner.model.response.riwayatorder.Transaksiuser
import com.abdigunawan.muapartner.ui.order.inprogress.InprogressFragment
import com.abdigunawan.muapartner.ui.order.pastorders.PastordersFragment

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(
        fm
    ) {

    var inprogressList: ArrayList<Transaksiuser>? = ArrayList()
    var pastOrdersList: ArrayList<Transaksiuser>? = ArrayList()

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment
        return when (position) {
            0 -> {
                    fragment = InprogressFragment()
                    return fragment
            }
            1 -> {
                    fragment = PastordersFragment()
                    return fragment
                }
            else -> {
                fragment = InprogressFragment()
                return fragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Sekarang"
            1 -> "Riwayat"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

}