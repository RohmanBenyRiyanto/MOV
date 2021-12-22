package com.rohmanbeny.mov.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.home.HomeActivity
import kotlinx.android.synthetic.main.activity_succes_mywallet.*

class SuccesTopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_mywallet)

        btn_home.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@SuccesTopUpActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }
    }
}