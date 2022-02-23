package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editpassword

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.activity_edit_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditPasswordActivity : AppCompatActivity(), EditPasswordContract.View {

    lateinit var presenter: EditPasswordPresenter
    var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_password)
        customTolbar()
        presenter = EditPasswordPresenter(this)
        initListener()
        initView()
    }

    private fun customTolbar() {
        toolbar.title = "Edit Password"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListener() {
        btnEdit.setOnClickListener {
            var oldPassword = editOldPassword.text.toString()
            var newPassword = editNewPassword.text.toString()
            var confirmPassword = editConfirmPassword.text.toString()

            if (oldPassword.isNullOrEmpty()) {
                editOldPassword.error = "Masukin Password lama dulu dong"
                editOldPassword.requestFocus()
            } else if (newPassword.isNullOrEmpty()) {
                editNewPassword.error = "Masukin Password baru dulu dong"
                editNewPassword.requestFocus()
            } else if (confirmPassword.isNullOrEmpty()) {
                editConfirmPassword.error = "Masukin konfirmasi Password dulu dong"
                editConfirmPassword.requestFocus()
            } else {
                presenter.submitPassword(oldPassword,newPassword,confirmPassword)
            }
        }
    }

    private fun initView() {
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    override fun oneditPasswordSuccess(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("YEAYY!!")
            .setContentText(message)
            .setConfirmClickListener(SweetAlertDialog.OnSweetClickListener() {
                it.dismissWithAnimation()
                this.finish()
            })
            .show()
    }

    override fun oneditPasswordFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("UPSSSS!!")
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