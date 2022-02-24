package com.abdigunawan.muapartner.ui.order.detailsorders

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FinishOrderPresenter (private val view: FinishOrderContract.View) : FinishOrderContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun finishOrder(transaksiId: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.finishTransaksi(transaksiId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.message.equals("Berhasil Diselesaikan")) {
                        view.onFinishSuccess(it.message)
                    } else {
                        view.onFinishFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onFinishFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}