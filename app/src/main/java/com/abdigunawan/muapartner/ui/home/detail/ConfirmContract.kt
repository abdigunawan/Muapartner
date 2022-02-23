package com.abdigunawan.muapartner.ui.home.detail

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView


interface ConfirmContract {

    interface View: BaseView {
        fun onConfirmSuccess(message: String)
        fun onConfirmFailed(message:String)
    }

    interface Presenter : ConfirmContract, BasePresenter {
        fun confirmTransaction(paketId : String , tanggalketemu : String ,jamketemu : String, lokasi : String,catatan : String )
    }
}