package com.abdigunawan.muapartner.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.ui.profile.makeupme.AboutActivity
import com.abdigunawan.muapartner.ui.profile.makeupme.BeriMasukanActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditPasswordActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditProfilActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditProfilPhotoActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.PaketActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListener()

    }

    private fun initListener() {

        layoutUbahProfil.setOnClickListener {
            val editProfil = Intent(activity, EditProfilActivity::class.java)
            startActivity(editProfil)
        }

        layoutUbahPassword.setOnClickListener {
            val editPassword = Intent(activity, EditPasswordActivity::class.java)
            startActivity(editPassword)
        }

        layoutUbahFotoProfil.setOnClickListener {
            val editFoto = Intent(activity, EditProfilPhotoActivity::class.java)
            startActivity(editFoto)
        }

        layoutAturPaket.setOnClickListener {
            val aturPaket = Intent(activity, PaketActivity::class.java)
            startActivity(aturPaket)
        }



        layoutTentangMakeupme.setOnClickListener {
            val aboutMakeupme = Intent(activity, AboutActivity::class.java)
            startActivity(aboutMakeupme)
        }

        layoutBeriMasukan.setOnClickListener {
            val beriMasukan = Intent(activity, BeriMasukanActivity::class.java)
            startActivity(beriMasukan)
        }


    }
}