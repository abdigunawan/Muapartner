package com.abdigunawan.muapartner.ui.home.detail

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.BuildConfig
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.home.Transaksiuser
import com.abdigunawan.muapartner.ui.MainActivity
import com.abdigunawan.muapartner.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_booking.*
import kotlinx.android.synthetic.main.item_pesananmasuk.*
import kotlinx.android.synthetic.main.item_pesananmasuk.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.text.SimpleDateFormat

class DetailBookingActivity : AppCompatActivity(),DeleteContract.View {

    lateinit var presenter : DeletePresenter
    var progressDialog: Dialog? = null
    private val detailtransaksi by lazy { intent.getSerializableExtra("detailtransaksi") as Transaksiuser }
    val formatDate = SimpleDateFormat("dd MMMM YYYY")
    val formatHour = SimpleDateFormat("HH.mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_booking)
        presenter = DeletePresenter(this)
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

        tvTitle.text = detailtransaksi.transaksiuserx.produk
        tvPrice.formatPrice(detailtransaksi.transaksiuserx.harga.toString())
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

        val fotopaket = BuildConfig.BASE_URL+"assets/img/mua/paket/" + detailtransaksi.transaksiuserx.foto
        Glide.with(this)
            .load(fotopaket)
            .apply(RequestOptions.centerCropTransform())
            .into(ivPoster)

    }

    private fun initListener() {

        btnTerima.setOnClickListener {
            val aturJadwal = Intent(this, AturJadwalActivity::class.java).putExtra("detailtransaksi", detailtransaksi)
            startActivity(aturJadwal)
        }

        btnCancel.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Opsss!!!")
                .setContentText("Kamu Yakin Ingin Batalkan Booking ini?")
                .setCancelButton("Batal", SweetAlertDialog.OnSweetClickListener {
                    it.dismissWithAnimation()
                })
                .setConfirmButton("Yakin", SweetAlertDialog.OnSweetClickListener {
                    presenter.batalTransaksi(detailtransaksi.id.toString(),null)
                })
                .show()
        }

    }

    override fun onBatalSuccess(message: String) {
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBatalfailed(message: String) {
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