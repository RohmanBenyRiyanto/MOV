package com.rohmanbeny.mov.checkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.home.HomeActivity
import kotlinx.android.synthetic.main.activity_succes_beli.*

class SuccesBeliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_beli)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@SuccesBeliActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }
    }
}