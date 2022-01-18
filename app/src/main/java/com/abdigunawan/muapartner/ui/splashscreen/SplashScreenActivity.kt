package com.abdigunawan.muapartner.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.ui.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val auth = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            startActivity(auth)
            finish()
        }, 3000)
    }
}