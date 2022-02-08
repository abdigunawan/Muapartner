package com.abdigunawan.muapartner.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.Navigation
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.request.RegisterRequest
import com.abdigunawan.muapartner.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    lateinit var kota : Spinner
    var gambar: Uri?= null
    var upload_sertifikat : Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initKota()
        initListener()

    }

    private fun initKota() {
        kota = activity?.findViewById(R.id.spinnerKota) as Spinner
        val isikota = arrayOf("Makassar","Maros","Gowa","Bone")
        kota.adapter = context?.let { ArrayAdapter<String>(it,R.layout.layout_spinner, isikota) }
    }

    private fun initListener() {

        btnSimpan.setOnClickListener {

            var namalengkap = etNamaLengkap.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var nohp = etNohp.text.toString()
            var alamat = etAlamat.text.toString()
            var norumah = etRumah.text.toString()
            var kota = spinnerKota.selectedItem.toString()
            val role = ""

            if (namalengkap.isNullOrEmpty()) {
                etNamaLengkap.error = "Masukkan Nama Dulu"
                etNamaLengkap.requestFocus()
            } else if (email.isNullOrEmpty()) {
                etEmail.error = "Masukkan Email Dulu"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Masukkan Password Dulu"
                etPassword.requestFocus()
            } else if (nohp.isNullOrEmpty()) {
                etNohp.error = "Masukkan Nomor HP Dulu"
                etNohp.requestFocus()
            } else if (alamat.isNullOrEmpty()) {
                etAlamat.error = "Masukkan Alamat Dulu"
                etAlamat.requestFocus()
            } else if (norumah.isNullOrEmpty()) {
                etRumah.error = "Masukkan Nomor Rumah Dulu"
                etRumah.requestFocus()
            } else if (kota.isNullOrEmpty()) {
                Toast.makeText(context, "Pilih Kota Dulu", Toast.LENGTH_SHORT).show()
                spinnerKota.requestFocus()
            } else {
                var data = RegisterRequest(
                    namalengkap,
                    email,
                    password,
                    nohp,
                    alamat,
                    norumah,
                    kota,
                    gambar,
                    upload_sertifikat,
                    role
                )


                var bundle = Bundle()
                bundle.putParcelable("data", data)
                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_verif, bundle)

                (activity as AuthActivity).toolbarSignupAddress()
            }
        }

        ivFotoProfil.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            gambar = data?.data

            Glide.with(this)
                .load(gambar)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFotoProfil)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Pilih Foto Dibatallan", Toast.LENGTH_SHORT).show()
        }
    }
}