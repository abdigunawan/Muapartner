package com.abdigunawan.muapartner.model.dummy

class HomeModel(pelanggan:String, paket:String, src:String, alamat:String, tanggal:String) {

    var pelanggan = ""
    var paket = ""
    var src = ""
    var alamat = ""
    var tanggal = ""

    init {
        this.pelanggan = pelanggan
        this.paket = paket
        this.src = src
        this.alamat = alamat
        this.tanggal = tanggal
    }

}