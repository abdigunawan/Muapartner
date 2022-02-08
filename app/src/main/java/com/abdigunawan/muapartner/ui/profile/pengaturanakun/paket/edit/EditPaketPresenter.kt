package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.edit

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

class EditPaketPresenter(private val view: EditPaketContract.View) : EditPaketContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun editPaket(editPaketRequest: EditPaketRequest, view: View) {
        this.view.showLoading()
        var namapaket = RequestBody.create(MediaType.parse("text/plain"),editPaketRequest.nama_paket)
        var deskripsi = RequestBody.create(MediaType.parse("text/plain"),editPaketRequest.deskripsi)
        var produk = RequestBody.create(MediaType.parse("text/plain"),editPaketRequest.produk)
        var harga = RequestBody.create(MediaType.parse("text/plain"),editPaketRequest.harga)

        if (editPaketRequest.foto == null) {
            val disposable = HttpClient.getInstance().getApi()!!.updateproduk(
                editPaketRequest.id,
                namapaket,
                deskripsi,
                produk,
                harga,
                null
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.view.dismissLoading()

                    if (it.message.equals("Berhasil Update Produk")) {
                        this.view.onEditPaketSuccess(it.message.toString())
                    }else {
                        this.view.onEditPaketFailed(it.message)
                    }
                },{
                    this.view.dismissLoading()
                    this.view.onEditPaketFailed(it.getErrorBodyMessage())
                })
            mCompositeDisposable!!.add(disposable)

        } else {

            var fotoprodukFile = File(editPaketRequest.foto?.path)
            var fotoprodukRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),fotoprodukFile)
            val fotoprodukParms = MultipartBody.Part.createFormData("foto",fotoprodukFile.name, fotoprodukRequestBody)

            val disposable = HttpClient.getInstance().getApi()!!.updateproduk(
                editPaketRequest.id,
                namapaket,
                deskripsi,
                produk,
                harga,
                fotoprodukParms
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.view.dismissLoading()

                    if (it.message.equals("Berhasil Update Produk")) {
                        this.view.onEditPaketSuccess(it.message.toString())
                    }else {
                        this.view.onEditPaketFailed(it.message)
                    }
                },{
                    this.view.dismissLoading()
                    this.view.onEditPaketFailed(it.getErrorBodyMessage())
                })
            mCompositeDisposable!!.add(disposable)
        }
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}