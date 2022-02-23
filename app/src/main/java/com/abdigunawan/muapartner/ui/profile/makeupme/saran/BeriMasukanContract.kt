package com.abdigunawan.muapartner.ui.profile.makeupme.saran

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView


interface BeriMasukanContract {

    interface View: BaseView {
        fun onBeriMasukanSuccess(message: String)
        fun onBeriMasukanFailed(message:String)
    }

    interface Presenter : BeriMasukanContract, BasePresenter {
        fun submitSaran(id_user: String?, saran: String)
    }
}