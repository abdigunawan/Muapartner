package com.abdigunawan.muapartner.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.utils.Cons
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == Cons.AUTH_SIGN_UP) {
            toolbarSignup()
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.fragmentSignin, true).build()

            Navigation.findNavController(findViewById(R.id.authHostFragment)).navigate(R.id.action_signup, null,navOptions)
        }
    }

    fun toolbarSignup() {
        toolbar.title = "Buat Akun";
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignupAddress() {
        toolbar.title = "Verifikasi Diri";
        toolbar.subtitle = "Temukan Wajah Terbaikmu"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_020202, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignupSuccess() {
        toolbar.visibility = View.GONE
    }
}