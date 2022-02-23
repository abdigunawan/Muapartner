package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editpassword

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView


interface EditPasswordContract {

    interface View: BaseView {
        fun oneditPasswordSuccess(message: String)
        fun oneditPasswordFailed(message:String)
    }

    interface Presenter : EditPasswordContract, BasePresenter {
        fun submitPassword(passwordlama:String, passwordbaru:String, passwordkonfirmasi: String)
    }
}