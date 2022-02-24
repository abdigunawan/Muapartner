package com.abdigunawan.muapartner.ui.order.detailsorders

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.riwayatorder.Transaksiuser
import com.abdigunawan.muapartner.ui.MainActivity
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.text.SimpleDateFormat

class DetailOrderActivity : AppCompatActivity(),FinishOrderContract.View {

    lateinit var presenter : FinishOrderPresenter
    var progressDialog: Dialog? = null
    private val detailtransaksi by lazy { intent.getSerializableExtra("detailtransaksi") as Transaksiuser }
    val formatDate = SimpleDateFormat("dd MMMM YYYY")
    val formatHour = SimpleDateFormat("HH.mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)
        presenter = FinishOrderPresenter(this)
        initToolbar()
        initListener()
        initDetail()
        initView()
    }

    private fun initView() {
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initToolbar() {
        toolbar.title = "Detail Booking"
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDetail(){

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val simpleHourFormat = SimpleDateFormat("hh:mm:ss")

        tvTitle.text = detailtransaksi.transaksiuser.produk
        tvPrice.formatPrice(detailtransaksi.transaksiuser.harga.toString())
        textView15.text = detailtransaksi.jumlah
        tvTotal.formatPrice(detailtransaksi.totalHarga)
        tvNama.text = detailtransaksi.user.name
        tvPhone.text = detailtransaksi.user.noHp
        tvAddress.text = detailtransaksi.user.alamat
        tvHouseNo.text = detailtransaksi.user.noRumah
        val date = simpleDateFormat.parse(detailtransaksi.tanggalAcara)
        val hour = simpleHourFormat.parse(detailtransaksi.jamAcara)
        tvtanggalKetemu.text = formatDate.format(date)
        tvJamKetemu.text = formatHour.format(hour)
        tvCatatan.text = detailtransaksi.catatan
        tvStatus.text = detailtransaksi.status

        if (detailtransaksi.status.equals("KONFIRMASI")) {
            btnTerima.isVisible = true
        } else if (detailtransaksi.status.equals("DIBATALKAN")) {
            btnTerima.isVisible = false
        } else if (detailtransaksi.status.equals("SELESAI")) {
            btnTerima.isVisible = false
        }

        if (detailtransaksi.status.equals("DIBATALKAN")) {
            tvStatus.setTextColor(Color.RED)
        } else if (detailtransaksi.status.equals("SELESAI")) {
            tvStatus.setTextColor(Color.GREEN)
        }

        val fotopaket = BuildConfig.BASE_URL+"assets/img/mua/paket/" + detailtransaksi.transaksiuser.foto
        Glide.with(this)
            .load(fotopaket)
            .apply(RequestOptions.centerCropTransform())
            .into(ivPoster)

    }

    private fun initListener() {

        btnTerima.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Yakin!!!")
                .setContentText("Kamu Yakin Booking ini udah selesai?")
                .setCancelButton("Batal", SweetAlertDialog.OnSweetClickListener {
                    it.dismissWithAnimation()
                })
                .setConfirmButton("Yakin", SweetAlertDialog.OnSweetClickListener {
                    presenter.finishOrder(detailtransaksi.id.toString())
                })
                .show()
        }

    }

    override fun onFinishSuccess(message: String) {
        finish()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFinishFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Opss!!")
            .setContentText(message)
            .show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}