package com.abdigunawan.muapartner.ui.home.detail

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ConfirmPresenter (private val view: ConfirmContract.View) : ConfirmContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun confirmTransaction(
        paketId: String,
        tanggalketemu: String,
        jamketemu: String,
        lokasi: String,
        catatan: String
    ) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.konfirmasiTransaksi(
            paketId,
            tanggalketemu,
            jamketemu,
            lokasi,
            catatan
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Berhasil Konfirmasi")) {
                        view.onConfirmSuccess(it.message)
                    } else {
                        view.onConfirmFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onConfirmFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}