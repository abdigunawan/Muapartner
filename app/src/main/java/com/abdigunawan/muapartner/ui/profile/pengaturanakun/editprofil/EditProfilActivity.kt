package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editprofil

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.request.EditProfilRequest
import com.abdigunawan.muapartner.model.response.editprofil.Ubahprofile
import com.abdigunawan.muapartner.model.response.login.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditProfilActivity : AppCompatActivity(), EditProfilContract.View {

    lateinit var kota : Spinner
    var fotoProfil: Uri?= null
    var progressDialog: Dialog? = null
    lateinit var presenter : EditProfilPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        presenter = EditProfilPresenter(this)
        customTolbar()
        initKota()
        initListener()
        initView()

    }

    private fun customTolbar() {
        toolbar.title = "Edit Profil"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initKota() {
        kota = findViewById(R.id.spinnerKota) as Spinner
        val isikota = arrayOf("Makassar","Maros","Gowa","Bone")
        kota.adapter = ArrayAdapter<String>(this,R.layout.layout_spinner, isikota)
        kota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var kotaTerpilih = isikota.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initListener() {
        ivFotoProfil.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
        btnEdit.setOnClickListener {
            var nama = editNamaLengkap.text.toString()
            var email = editEmail.text.toString()
            var nohp = etNohp.text.toString()
            var alamat = editAlamat.text.toString()
            var norumah = editRumah.text.toString()
            var kota = spinnerKota.selectedItem.toString()
            val role = ""

            if (nama.isNullOrEmpty()) {
                editNamaLengkap.error = "Masukkan Nama Dulu"
                editNamaLengkap.requestFocus()
            } else if (email.isNullOrEmpty()) {
                editEmail.error = "Masukkan Email Dulu"
                editEmail.requestFocus()
            } else if (nohp.isNullOrEmpty()) {
                etNohp.error = "Masukkan Password Dulu"
                etNohp.requestFocus()
            } else if (alamat.isNullOrEmpty()) {
                editAlamat.error = "Masukkan Alamat Dulu"
                editAlamat.requestFocus()
            } else if (norumah.isNullOrEmpty()) {
                editRumah.error = "Masukkan Nomor Rumah Dulu"
                editRumah.requestFocus()
            } else if (kota.isNullOrEmpty()) {
                Toast.makeText(this, "Pilih Kota Dulu", Toast.LENGTH_SHORT).show()
                spinnerKota.requestFocus()
            } else if (fotoProfil == null) {
                var data = EditProfilRequest(
                    nama,
                    email,
                    nohp,
                    alamat,
                    norumah,
                    kota,
                    role,
                    null
                )

                presenter.editProfil(data,it)
            } else {
                var data = EditProfilRequest(
                    nama,
                    email,
                    nohp,
                    alamat,
                    norumah,
                    kota,
                    role,
                    fotoProfil
                )

                presenter.editProfil(data,it)
            }
        }
    }

    private fun initView() {

        var user = MuaPartner.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.gambar.isNullOrEmpty()) {
            val profilMua = BuildConfig.BASE_URL+"assets/img/mua/" + userResponse.gambar
            Glide.with(this)
                .load(profilMua)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFotoProfil)
        }

        editNamaLengkap.setText(userResponse.name)
        editEmail.setText(userResponse.email)
        etNohp.setText(userResponse.noHp)
        editAlamat.setText(userResponse.alamat)
        editRumah.setText(userResponse.noRumah)

        progressDialog = Dialog(this)
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
            fotoProfil = data?.data

            Glide.with(this)
                .load(fotoProfil)
                .apply(RequestOptions.circleCropTransform())
                .into(ivFotoProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Pilih Foto Dibatallan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEditProfilSuccess(editProfilResponse: Ubahprofile) {
        val gson = Gson()
        val json = gson.toJson(editProfilResponse)
        MuaPartner.getApp().setUser(json)

        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("YEAYYY!!!")
            .setContentText("Berhasil Edit Profil")
            .setConfirmClickListener(SweetAlertDialog.OnSweetClickListener() {
                it.dismissWithAnimation()
                this.finish()
            })
            .show()
    }

    override fun onEditProfilFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("UPSS!!!!")
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