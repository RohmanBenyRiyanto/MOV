package com.rohmanbeny.mov.checkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.model.Checkout
import com.rohmanbeny.mov.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {

    var statusA3:Boolean = false
    var statusA4:Boolean = false
    var total:Int = 0

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        val data = intent.getParcelableExtra<Film>("data")
        tv_kursi.text = data!!.judul

        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "50000")
                datalist.add(data)
            }
        }

        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4", "50000")
                datalist.add(data)
            }
        }

        btn_home.setOnClickListener {
            val intent = Intent (this, CheckOutActivity::class.java)
                .putExtra("data", datalist)
            startActivity(intent)
        }
        iv_close.setOnClickListener {
            finish()
        }

    }

    private fun beliTiket(total: Int) {
        if (total == 0) {
            btn_home.setText("Beli tiket")
            btn_home.visibility = View.INVISIBLE
        } else {
            btn_home.setText("Beli tiket (" + total +")")
            btn_home.visibility = View.VISIBLE
        }
    }
}