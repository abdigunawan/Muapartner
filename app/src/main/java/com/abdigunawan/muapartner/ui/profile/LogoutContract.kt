package com.abdigunawan.muapartner.ui.profile

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.AddPaketRequest


interface LogoutContract {

    interface View: BaseView {
        fun onLogoutSuccess(message:String)
        fun onLogoutFailed(message:String)
    }

    interface Presenter : LogoutContract, BasePresenter {
        fun Logout()
    }
}