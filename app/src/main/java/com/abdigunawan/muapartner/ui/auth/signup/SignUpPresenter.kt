package com.abdigunawan.muapartner.ui.auth.signup

import android.net.Uri
import android.view.View
import com.abdigunawan.muapartner.model.request.RegisterRequest
import com.abdigunawan.muapartner.model.response.login.X0
import com.abdigunawan.muapartner.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpPresenter (private val view: SignUpContract.View) : SignUpContract.Presenter {

    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, filePath: Uri, viewParams: View) {
        view.showLoading()
        var profileImageFile = File(filePath.path)
        var profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),profileImageFile)
        val profileImageParms = MultipartBody.Part.createFormData("file",profileImageFile.name, profileImageRequestBody)

        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.no_hp,
            registerRequest.alamat,
            registerRequest.no_rumah,
            registerRequest.kota,
            profileImageParms,
            profileImageParms,
            registerRequest.roles
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Berhasil Register")) {
                        it.x0.let { it1 -> view.onRegisterSuccess(it1 as X0?, viewParams) }
                    } else {
                        view.onRegisterFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}