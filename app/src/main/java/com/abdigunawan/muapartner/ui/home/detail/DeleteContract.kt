package com.abdigunawan.muapartner.ui.home.detail

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView


interface DeleteContract {

    interface View: BaseView {
        fun onBatalSuccess(message: String)
        fun onBatalfailed(message:String)
    }

    interface Presenter : DeleteContract, BasePresenter {
        fun batalTransaksi(transaksiId : String, status : String?)
    }
}