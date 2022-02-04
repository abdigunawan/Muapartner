package com.abdigunawan.muapartner.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.login.User
import com.abdigunawan.muapartner.ui.profile.makeupme.AboutActivity
import com.abdigunawan.muapartner.ui.profile.makeupme.BeriMasukanActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditPasswordActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditProfilActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.EditProfilPhotoActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.PaketActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
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

        initUser()
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

    private fun initUser() {
        var user = MuaPartner.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.gambar.isNullOrEmpty()) {
            val profilMua = BuildConfig.BASE_URL+"assets/img/mua/" + userResponse.gambar
            Glide.with(requireActivity())
                .load(profilMua)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFotoProfil)
        }

        tvMuaLogin.setText(userResponse.name)
        tvEmailLogin.setText(userResponse.email)
    }
}