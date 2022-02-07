package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.AddPaketRequest
import com.abdigunawan.muapartner.model.response.profile.paket.GetPaketResponse
import com.abdigunawan.muapartner.model.response.profile.paket.Produk


interface PaketContract {

    interface View: BaseView {
        fun onPaketSuccess(getPaketResponse: GetPaketResponse)
        fun onPaketFailed(message:String)
    }

    interface Presenter : PaketContract, BasePresenter {
        fun getPaket()
    }
}