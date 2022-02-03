package com.abdigunawan.muapartner.ui.home.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import java.util.*
import android.view.ViewGroup
import android.widget.Toast
import com.abdigunawan.muapartner.R
import kotlinx.android.synthetic.main.fragment_atur_jadwal.*
import java.text.SimpleDateFormat

class AturJadwalFragment : Fragment() {

    val formatDate = SimpleDateFormat("dd MMMM YYYY")
    val formatHour = SimpleDateFormat("HH.mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atur_jadwal, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getTanggal()
        getJam()

        btnKonfirmasi.setOnClickListener {
            Toast.makeText(context, "Jadwal Makeup : " + etTanggal.text.toString() + ", " + etJam.text.toString() + "\nCatatan : " + etCatatan.text.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun getTanggal() {
        etTanggal.setOnClickListener {
            val getDate = Calendar.getInstance()
            val dPickerDialog = DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

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
            val tsl = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, i)
                cal.set(Calendar.MINUTE, i2)
                val jam : String = formatHour.format(cal.time)

                etJam.setText(jam)
            }, gethour.get(Calendar.HOUR_OF_DAY),gethour.get(Calendar.MINUTE), true)

            tsl.show()
        }
    }

}

