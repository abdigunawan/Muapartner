package com.abdigunawan.muapartner.ui.profile

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LogoutPresenter(private val view: LogoutContract.View) : LogoutContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun Logout() {

        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()

                if (it.message.equals("Logout Berhasil")) {
                    view.onLogoutSuccess(it.message.toString())
                }else {
                    view.onLogoutFailed(it.message)
                }
            },{
                view.dismissLoading()
                view.onLogoutFailed(it.getErrorBodyMessage())
            })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}