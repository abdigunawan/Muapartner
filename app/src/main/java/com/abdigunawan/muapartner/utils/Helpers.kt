package com.abdigunawan.muapartner.utils

import android.widget.TextView
import java.text.DecimalFormat

object Helpers {
    fun TextView.formatPrice(value : String) {
        this.text = getCurrendIDR(java.lang.Double.parseDouble(value))
    }
    fun getCurrendIDR(price : Double): String{
        val format = DecimalFormat("#,###,###")
        return "Rp "+format.format(price).replace(",".toRegex(),".")
    }
}