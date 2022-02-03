package com.abdigunawan.muapartner.ui.auth.signup

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.request.RegisterRequest
import com.abdigunawan.muapartner.model.response.login.X0
import com.abdigunawan.muapartner.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_up_verif.*

class SignUpVerifFragment : Fragment(), SignUpContract.View {

    lateinit var data : RegisterRequest
    lateinit var presenter: SignUpPresenter
    var progressDialog: Dialog?=null
    var upload_sertifikat : Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_verif, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initView()
        initListener()



    }

    private fun initListener() {

        btnSignupNow.isVisible = false
        btnSignupNow.setOnClickListener {

//            data.let {
//                it.upload_sertifikat = upload_sertifikat
//            }
//
//            presenter.submitRegister(data, data)


            Navigation.findNavController(it)
                .navigate(R.id.action_signup_success, null)

            (activity as AuthActivity).toolbarSignupSuccess()
        }

        ivSertifikat.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .start()
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            btnSignupNow.isVisible = true

            upload_sertifikat = data?.data

            Glide.with(this)
                .load(upload_sertifikat)
                .apply(RequestOptions.fitCenterTransform())
                .into(ivSertifikat)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            btnSignupNow.isVisible = false
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            btnSignupNow.isVisible = false
            Toast.makeText(context, "Pilih Foto Dibatallan", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRegisterSuccess(loginResponse: X0?, view: View) {
        MuaPartner.getApp().setToken(loginResponse!!.token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        MuaPartner.getApp().setUser(json)
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)

        (activity as AuthActivity).toolbarSignupSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}