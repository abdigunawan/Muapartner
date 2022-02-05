package com.abdigunawan.muapartner.ui.profile.pengaturanakun.paket.add

import android.view.View
import com.abdigunawan.muapartner.model.request.AddPaketRequest
import com.abdigunawan.muapartner.network.HttpClient
import com.abdigunawan.muapartner.ui.auth.signup.SignUpContract
import com.abdigunawan.muapartner.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddPaketPresenter(private val view: AddPaketContract.View) : AddPaketContract.Presenter {


    private var mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun addPaket(addPaketRequest: AddPaketRequest, viewParams: View) {

        view.showLoading()

        var namapaket = RequestBody.create(MediaType.parse("text/plain"),addPaketRequest.nama_paket)
        var deskripsi = RequestBody.create(MediaType.parse("text/plain"),addPaketRequest.deskripsi)
        var produk = RequestBody.create(MediaType.parse("text/plain"),addPaketRequest.produk)
        var harga = RequestBody.create(MediaType.parse("text/plain"),addPaketRequest.harga)
        var fotoprodukFile = File(addPaketRequest.foto?.path)
        var fotoprodukRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),fotoprodukFile)
        val fotoprodukParms = MultipartBody.Part.createFormData("foto",fotoprodukFile.name, fotoprodukRequestBody)

        val disposable = HttpClient.getInstance().getApi()!!.addproduk(
            namapaket,
            deskripsi,
            produk,
            harga,
            fotoprodukParms
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()

                if (it.message.equals("Berhasil Tambah Produk")) {
                    view.onAddPaketSuccess(it.message.toString())
                }else {
                    view.onAddPaketFailed(it.message)
                }
            },{
                view.dismissLoading()
                view.onAddPaketFailed(it.getErrorBodyMessage())
            })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}