package com.abdigunawan.muapartner.ui.auth.signup

import android.net.Uri
import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.RegisterRequest
import com.abdigunawan.muapartner.model.response.login.X0


interface SignUpContract {
    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: X0?, view:android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter : SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest,filePath:Uri, view:android.view.View)
    }
}