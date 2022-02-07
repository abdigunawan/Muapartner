package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.edit

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.AddPaketRequest
import com.abdigunawan.muapartner.model.request.EditPaketRequest


interface EditPaketContract {

    interface View: BaseView {
        fun onEditPaketSuccess(message:String)
        fun onEditPaketFailed(message:String)
    }

    interface Presenter : EditPaketContract, BasePresenter {
        fun editPaket(editPaketRequest: EditPaketRequest , view:android.view.View)
    }
}