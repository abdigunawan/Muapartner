package com.abdigunawan.muapartner.ui.auth.signin

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.response.login.X0

interface SignInContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: X0?)
        fun onLoginFailed(message:String)
    }

    interface Presenter : SignInContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}