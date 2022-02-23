package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editprofil

import com.abdigunawan.muapartner.base.BasePresenter
import com.abdigunawan.muapartner.base.BaseView
import com.abdigunawan.muapartner.model.request.EditProfilRequest
import com.abdigunawan.muapartner.model.response.editprofil.Ubahprofile

interface EditProfilContract {

    interface View: BaseView {
        fun onEditProfilSuccess(editProfilResponse: Ubahprofile)
        fun onEditProfilFailed(message:String)
    }

    interface Presenter : EditProfilContract, BasePresenter {
        fun editProfil(editProfilRequest: EditProfilRequest, view:android.view.View)
    }
}