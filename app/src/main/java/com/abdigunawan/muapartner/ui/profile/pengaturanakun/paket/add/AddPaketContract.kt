package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.AddPaketRequest


interface AddPaketContract {

    interface View: BaseView {
        fun onAddPaketSuccess(message:String)
        fun onAddPaketFailed(message:String)
    }

    interface Presenter : AddPaketContract, BasePresenter {
        fun addPaket(addPaketRequest : AddPaketRequest, view:android.view.View)
    }
}