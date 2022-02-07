package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.detail

import android.view.View
import com.abdigunawan.muapartner.model.request.EditPaketRequest
import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DeletePaketPresenter(private val view: DeletePaketContract.View) : DeletePaketContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun deletePaket(id: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.deleteproduk(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()

                if (it.message.equals("Berhasil dihapus")) {
                    view.onDeletePaketSuccess(it.message)
                }else {
                    view.onDeletePaketFailed(it.message)
                }
            },{
                view.dismissLoading()
                view.onDeletePaketFailed(it.getErrorBodyMessage())
            })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}