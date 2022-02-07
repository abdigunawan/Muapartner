package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaketPresenter (private val view: PaketContract.View) : PaketContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getPaket() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getproduk()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Data berhasil ditampilkan")) {
                        it.produk.let { data -> view.onPaketSuccess(it) }
                    } else {
                        view.onPaketFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onPaketFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}