package com.abdigunawan.muapartner.ui.testimoni

import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TestimoniPresenter (private val view: TestimoniContract.View) : TestimoniContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTestimoni() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getTestimoni()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.message.equals("Data Berhasil ditampilkan")) {
                        it.testimoni.let { data -> view.onTestimoniSuccess(it) }
                    } else {
                        view.onTestimoniFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onTestimoniFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}