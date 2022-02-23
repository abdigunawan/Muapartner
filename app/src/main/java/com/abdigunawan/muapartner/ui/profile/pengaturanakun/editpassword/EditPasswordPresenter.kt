package com.abdigunawan.muapartner.ui.profile.pengaturanakun.editpassword

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EditPasswordPresenter (private val view:EditPasswordContract.View) : EditPasswordContract.Presenter {

    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitPassword(passwordlama: String, passwordbaru: String, passwordkonfirmasi: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.editpassword(
            passwordlama,
            passwordbaru,
            passwordkonfirmasi
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message == "Berhasil merubah password") {
                        it.message.let { data -> view.oneditPasswordSuccess(data) }
                    } else {
                        view.oneditPasswordFailed(it.message)
                    }
                },{
                    view.dismissLoading()
                    view.oneditPasswordFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}