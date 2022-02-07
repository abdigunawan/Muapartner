package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.EditPaketRequest


interface DeletePaketContract {

    interface View: BaseView {
        fun onDeletePaketSuccess(message:String)
        fun onDeletePaketFailed(message:String)
    }

    interface Presenter : DeletePaketContract, BasePresenter {
        fun deletePaket(id: String)
    }
}