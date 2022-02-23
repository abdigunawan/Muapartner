package com.abdigunawan.muapartner.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.login.User
import com.abdigunawan.muapartner.ui.auth.AuthActivity
import com.abdigunawan.muapartner.ui.profile.makeupme.AboutActivity
import com.abdigunawan.muapartner.ui.profile.makeupme.saran.BeriMasukanActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.editpassword.EditPasswordActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.editprofil.EditProfilActivity
import com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.PaketActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(),LogoutContract.View {

    lateinit var presenter : LogoutPresenter
    var progressDialog: Dialog? = null

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

        presenter = LogoutPresenter(this)
        initUser()
        initListener()

        initView()

    }

    override fun onResume() {
        super.onResume()
        initUser()
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

    private fun initListener() {

        layoutUbahProfil.setOnClickListener {
            val editProfil = Intent(activity, EditProfilActivity::class.java)
            startActivity(editProfil)
        }


        layoutUbahPassword.setOnClickListener {
            val editPassword = Intent(activity, EditPasswordActivity::class.java)
            startActivity(editPassword)
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

        btnLogout.setOnClickListener {

            SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Logout")
                .setContentText("Kamu Yakin Ingin Logout?")
                .setCancelButton("Batal", SweetAlertDialog.OnSweetClickListener {
                    it.dismissWithAnimation()
                })
                .setConfirmButton("Yakin", SweetAlertDialog.OnSweetClickListener {
                    presenter.Logout()
                })
                .show()

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

    override fun onLogoutSuccess(message: String) {
        MuaPartner.getApp().setToken(null)
        MuaPartner.getApp().setUser(null)

        val login = Intent(activity, AuthActivity::class.java)
        startActivity(login)
        activity?.finish()
    }

    override fun onLogoutFailed(message: String) {
        SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Gagal Log Out")
            .setContentText(message)
            .show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}