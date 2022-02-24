package com.abdigunawan.muapartner.ui.testimoni

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.response.profile.paket.GetPaketResponse
import com.abdigunawan.muapartner.model.response.testimoni.TestimoniResponse


interface TestimoniContract {

    interface View: BaseView {
        fun onTestimoniSuccess(testimoniResponse: TestimoniResponse)
        fun onTestimoniFailed(message:String)
    }

    interface Presenter : TestimoniContract, BasePresenter {
        fun getTestimoni()
    }
}