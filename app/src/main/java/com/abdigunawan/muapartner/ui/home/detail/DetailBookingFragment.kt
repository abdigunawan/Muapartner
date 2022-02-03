package com.abdigunawan.muapartner.ui.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.fragment_detail_booking.*

class DetailBookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_booking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnTerima.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.actionterimabooking, null)

            (activity as DetailBookingActivity).toolbarAturJadwal()
        }

    }

}