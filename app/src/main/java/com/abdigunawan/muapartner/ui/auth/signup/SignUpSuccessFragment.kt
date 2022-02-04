package com.abdigunawan.muapartner.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_up_success.*

class SignUpSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnToLogin.setOnClickListener {
            activity?.finish()
        }
    }

}