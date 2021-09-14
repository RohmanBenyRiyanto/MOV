package com.rohmanbeny.mov.checkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.model.Checkout
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckOutActivity : AppCompatActivity() {

    private var datalist = ArrayList<Checkout>()
    private var total :Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences (this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in datalist.indices) {
            total += datalist[a].harga!!.toInt()
        }

        datalist.add(Checkout("Total harus dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(datalist) {

        }
//        iv_close.setOnClickListener {
//            finish()
//        }

        btn_tiket.setOnClickListener {
            val intent = Intent(this, SuccesBeliActivity::class.java)
            startActivity(intent)
        }
    }
}