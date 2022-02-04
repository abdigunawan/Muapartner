package com.abdigunawan.muapartner.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.login.X0
import com.abdigunawan.muapartner.ui.MainActivity
import com.abdigunawan.muapartner.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(),SignInContract.View {

    lateinit var presenter: SignInPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignInPresenter(this)
        if (!MuaPartner.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }
        initListener()
        initView()
    }

    private fun initListener() {
        btnSignin.setOnClickListener {

            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (email.isNullOrEmpty()) {
                etEmail.error = "Masukin Email Dulu Dong"
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Masukin Password Dulu Dong"
            } else {
                presenter.submitLogin(email,password)
            }
        }

        tvDaftar.setOnClickListener {
            Toast.makeText(context, "Membuka WhatsApp ", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6282293204972/?text=Halo%20Min!%0ASaya%20mempunyai%20kendala%20terkait%20akun%20makeup.me%20,%20apakah%20bisa%20dibantu?"))
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }
    }

    override fun onLoginSuccess(loginResponse: X0?) {
        MuaPartner.getApp().setToken(loginResponse!!.token)

        val gson = Gson()
        val json = gson.toJson(loginResponse?.user)
        MuaPartner.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
//        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Gagal Login")
            .setContentText(message)
            .show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

}