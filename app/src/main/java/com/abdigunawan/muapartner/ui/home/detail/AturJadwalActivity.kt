package com.abdigunawan.muapartner.ui.home.detail

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.response.home.Transaksiuser
import com.abdigunawan.muapartner.ui.MainActivity
import kotlinx.android.synthetic.main.activity_atur_jadwal.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AturJadwalActivity : AppCompatActivity(), ConfirmContract.View {

    private val detailtransaksi by lazy { intent.getSerializableExtra("detailtransaksi") as Transaksiuser }
    lateinit var presenter : ConfirmPresenter
    var progressDialog: Dialog? = null
    val formatDate = SimpleDateFormat("dd MMMM YYYY")
    val formatHour = SimpleDateFormat("HH.mm")
    lateinit var senddate : String
    lateinit var sendtime : String
    val sendformatDate = SimpleDateFormat("YYYY-MM-dd")
    val sendformatHour = SimpleDateFormat("HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atur_jadwal)
        presenter = ConfirmPresenter(this)
        getTanggal()
        getJam()
        initListener()
        initToolbar()
        initView()
    }

    private fun initListener() {

        btnKonfirmasi.setOnClickListener {

            var tanggalKetemu = etTanggal.text.toString()
            var jamketemu = etJam.text.toString()
            var lokasi = etLokasi.text.toString()
            var catatan = etCatatan.text.toString()

            if (tanggalKetemu.isNullOrEmpty()) {
                etTanggal.error = "Masukkan Tanggal Ketemu Dulu"
                etTanggal.requestFocus()
            } else if (jamketemu.isNullOrEmpty()) {
                etJam.error = "Masukkan Jam Ketemu Dulu"
                etJam.requestFocus()
            } else if (lokasi.isNullOrEmpty()) {
                etLokasi.error = "Masukkan Lokasi Ketemu Dulu"
                etLokasi.requestFocus()
            } else if (catatan.isNullOrEmpty()) {
                etCatatan.error = "Masukkan Catatan Dulu Dong"
                etCatatan.requestFocus()
            } else {
                presenter.confirmTransaction(
                    detailtransaksi.id.toString(),
                    senddate,
                    sendtime,
                    lokasi,
                    catatan
                )
            }
        }
    }

    private fun getTanggal() {
        etTanggal.setOnClickListener {
            val getDate = Calendar.getInstance()
            val dPickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val c = Calendar.getInstance()
                c.set(Calendar.YEAR, i)
                c.set(Calendar.MONTH, i2)
                c.set(Calendar.DAY_OF_MONTH, i3)
                val date : String = formatDate.format(c.time)
                senddate = sendformatDate.format(c.time)

                etTanggal.setText(date)
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))

            dPickerDialog.show()
        }
    }

    private fun getJam() {
        etJam.setOnClickListener {
            val gethour = Calendar.getInstance()
            val tsl = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, i)
                cal.set(Calendar.MINUTE, i2)
                val jam : String = formatHour.format(cal.time)
                sendtime = sendformatHour.format(cal.time)

                etJam.setText(jam)
            }, gethour.get(Calendar.HOUR_OF_DAY),gethour.get(Calendar.MINUTE), true)

            tsl.show()
        }
    }

    private fun initToolbar() {
            toolbar.title = "Atur Jadwal Ketemu"
            toolbar.subtitle = "Temukan Wajah Terbaikmu"
            toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
            toolbar.setNavigationOnClickListener { onBackPressed() }
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

    override fun onConfirmSuccess(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("YEAYYYY!!")
            .setContentText(message)
            .setConfirmButton("Okayy", SweetAlertDialog.OnSweetClickListener {
                it.dismissWithAnimation()
                val gohome = Intent(this, MainActivity::class.java)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/62" + detailtransaksi.user.noHp + "/?text=Halo%20Kak%20" + detailtransaksi.user.name + "%0ASaya%20telah%20mengonfirmasi%20pesanan%20kamu"))
                startActivity(gohome)
                startActivity(intent)
            })
            .show()
    }

    override fun onConfirmFailed(message: String) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("GAGAL")
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