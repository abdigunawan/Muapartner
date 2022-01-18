package com.abdigunawan.muapartner.ui.auth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.ui.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignin.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            startActivity(home)
            this.finish()
        }

        tvDaftar.setOnClickListener {
            Toast.makeText(this, "Membuka WhatsApp ", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6282293204972/?text=Halo%20Min!%0ASaya%20ingin%20bergabung%20menjadi%20mitra%20makeup.me%20,%20apakah%20bisa%20dibantu?"))
            startActivity(intent)
        }
    }
}