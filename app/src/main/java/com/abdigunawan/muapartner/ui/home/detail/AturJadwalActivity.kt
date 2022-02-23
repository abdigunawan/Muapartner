package com.abdigunawan.muapartner.ui.home.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.activity_atur_jadwal.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AturJadwalActivity : AppCompatActivity() {

    val formatDate = SimpleDateFormat("dd MMMM YYYY")
    val formatHour = SimpleDateFormat("HH.mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atur_jadwal)
        getTanggal()
        getJam()
        initListener()
        initToolbar()
    }

    private fun initListener() {

        btnKonfirmasi.setOnClickListener {
            Toast.makeText(this, "Jadwal Makeup : " + etTanggal.text.toString() + ", " + etJam.text.toString() + "\nCatatan : " + etCatatan.text.toString(), Toast.LENGTH_SHORT).show()
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

}