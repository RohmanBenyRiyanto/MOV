package com.rohmanbeny.mov

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.rohmanbeny.mov.onboarding.OnboardingOneActivity

/*
    This is Splash Screen
*/

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity,
                OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}

