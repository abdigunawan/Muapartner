package com.abdigunawan.muapartner.ui.home

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.response.home.HomeResponse


interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message:String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}