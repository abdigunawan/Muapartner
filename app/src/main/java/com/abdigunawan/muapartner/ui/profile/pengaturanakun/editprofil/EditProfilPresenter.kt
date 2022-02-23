package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editprofil

import android.view.View
import com.abdigunawan.muapartner.MuaPartner
import com.abdigunawan.muapartner.model.request.EditProfilRequest
import com.abdigunawan.muapartner.model.response.login.User
import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfilPresenter(private val view: EditProfilContract.View) : EditProfilContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun editProfil(editProfilRequest: EditProfilRequest, viewParams: View) {
        view.showLoading()

        var name = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.name)
        var email = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.email)
        var nohp = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.no_hp)
        var alamat = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.alamat)
        var norumah = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.no_rumah)
        var kota = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.kota)
        var role = RequestBody.create(MediaType.parse("text/plain"),editProfilRequest.roles)

        var user = MuaPartner.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (editProfilRequest.gambar == null) {
            val disposable = HttpClient.getInstance().getApi()!!.editprofil(
                userResponse.id.toString(),
                name,
                email,
                nohp,
                alamat,
                norumah,
                kota,
                null,
                role

            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.dismissLoading()

                        if (it.message.equals("Berhasil Ubah Profile")) {
                            it.ubahprofile.let { it1 -> view.onEditProfilSuccess(it1) }
                        } else {
                            view.onEditProfilFailed(it.message)
                        }

                    },
                    {
                        view.dismissLoading()
                        view.onEditProfilFailed(it.getErrorBodyMessage())
                    })
            mCompositeDisposable!!.add(disposable)

        } else {

            var profileImageFile = File(editProfilRequest.gambar?.path)
            var profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),profileImageFile)
            val profileImageParms = MultipartBody.Part.createFormData("gambar",profileImageFile.name, profileImageRequestBody)


            val disposable = HttpClient.getInstance().getApi()!!.editprofil(
                userResponse.id.toString(),
                name,
                email,
                nohp,
                alamat,
                norumah,
                kota,
                profileImageParms,
                role

            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.dismissLoading()

                        if (it.message.equals("Berhasil Ubah Profile")) {
                            it.ubahprofile.let { it1 -> view.onEditProfilSuccess(it1) }
                        } else {
                            view.onEditProfilFailed(it.message)
                        }

                    },
                    {
                        view.dismissLoading()
                        view.onEditProfilFailed(it.getErrorBodyMessage())
                    })
            mCompositeDisposable!!.add(disposable)

        }
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}