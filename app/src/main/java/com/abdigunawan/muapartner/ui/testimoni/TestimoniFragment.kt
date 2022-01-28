package com.abdigunawan.muapartner.ui.testimoni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.databinding.FragmentOrderBinding

class TestimoniFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_testimoni, container, false)
        return root
    }

}