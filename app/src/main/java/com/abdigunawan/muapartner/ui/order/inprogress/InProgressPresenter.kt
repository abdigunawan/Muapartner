package com.abdigunawan.muapartner.ui.order.inprogress

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InProgressPresenter (private val view:InProgressContract.View) : InProgressContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getRiwayatOrder()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Data Berhasil ditampilkan")) {
                        it.transaksiuser.let { data -> view.onTransactionSuccess(it) }
                    } else {
                        view.onTransactionFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onTransactionFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }
}