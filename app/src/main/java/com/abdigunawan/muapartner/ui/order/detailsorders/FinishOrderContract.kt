package com.abdigunawan.muapartner.ui.order.detailsorders

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView


interface FinishOrderContract {

    interface View: BaseView {
        fun onFinishSuccess(message: String)
        fun onFinishFailed(message:String)
    }

    interface Presenter : FinishOrderContract, BasePresenter {
        fun finishOrder(transaksiId : String)
    }
}