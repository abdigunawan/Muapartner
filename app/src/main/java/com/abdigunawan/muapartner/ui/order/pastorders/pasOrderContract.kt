package com.abdigunawan.muapartner.ui.order.pastorders

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.response.riwayatorder.RiwayatOrderResponse

interface pasOrderContract {
    interface View : BaseView {
        fun onTransactionSuccess(riwayatOrderResponse: RiwayatOrderResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : pasOrderContract, BasePresenter {
        fun getTransaction()
    }
}