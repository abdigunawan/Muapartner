package com.abdigunawan.muapartner.ui.order.inprogress

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.response.riwayatorder.RiwayatOrderResponse

interface InProgressContract {
    interface View : BaseView {
        fun onTransactionSuccess(riwayatOrderResponse: RiwayatOrderResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : InProgressContract, BasePresenter {
        fun getTransaction()
    }
}