package com.abdigunawan.muapartner.ui.home.detail

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DeletePresenter (private val view: DeleteContract.View) : DeleteContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun batalTransaksi(transaksiId: String, status: String?) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.batalkanTransaksi(transaksiId,null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Berhasil Dibatalkan")) {
                        view.onBatalSuccess(it.message)
                    } else {
                        view.onBatalfailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onBatalfailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}