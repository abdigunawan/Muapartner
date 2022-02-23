package com.abdigunawan.muapartner.ui.profile.makeupme.saran

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BeriMasukanPresenter (private val view:BeriMasukanContract.View) : BeriMasukanContract.Presenter {

    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun submitSaran(id_user: String?, saran: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.saran(null, saran)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message == "Berhasil mengirim saran") {
                        it.message.let { data -> view.onBeriMasukanSuccess(data) }
                    } else {
                        view.onBeriMasukanFailed(it.message)
                    }
                },{
                    view.dismissLoading()
                    view.onBeriMasukanFailed(it.getErrorBodyMessage())
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