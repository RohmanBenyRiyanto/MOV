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
    var statusB1:Boolean = false
    var statusC3:Boolean = false
    var statusC4:Boolean = false
    var statusD1:Boolean = false
    var statusD2:Boolean = false
    var statusD3:Boolean = false
    var statusD4:Boolean = false
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
                statusA4 = false
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

        b1.setOnClickListener {
            if (statusB1) {
                b1.setImageResource(R.drawable.ic_rectangle_empty)
                statusB1 = false
                total -= 1
                beliTiket(total)
            } else {
                b1.setImageResource(R.drawable.ic_rectangle_selected)
                statusB1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B1", "47000")
                datalist.add(data)
            }
        }

        c3.setOnClickListener {
            if (statusC3) {
                c3.setImageResource(R.drawable.ic_rectangle_empty)
                statusC3 = false
                total -= 1
                beliTiket(total)
            } else {
                c3.setImageResource(R.drawable.ic_rectangle_selected)
                statusC3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C3", "45000")
                datalist.add(data)
            }
        }

        c4.setOnClickListener {
            if (statusC4) {
                c4.setImageResource(R.drawable.ic_rectangle_empty)
                statusC4 = false
                total -= 1
                beliTiket(total)
            } else {
                c4.setImageResource(R.drawable.ic_rectangle_selected)
                statusC4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C4", "45000")
                datalist.add(data)
            }
        }

        d1.setOnClickListener {
            if (statusD1) {
                d1.setImageResource(R.drawable.ic_rectangle_empty)
                statusD1 = false
                total -= 1
                beliTiket(total)
            } else {
                d1.setImageResource(R.drawable.ic_rectangle_selected)
                statusD1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D1", "40000")
                datalist.add(data)
            }
        }

        d2.setOnClickListener {
            if (statusD2) {
                d2.setImageResource(R.drawable.ic_rectangle_empty)
                statusD2 = false
                total -= 1
                beliTiket(total)
            } else {
                d2.setImageResource(R.drawable.ic_rectangle_selected)
                statusD2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D2", "40000")
                datalist.add(data)
            }
        }

        d3.setOnClickListener {
            if (statusD3) {
                d3.setImageResource(R.drawable.ic_rectangle_empty)
                statusD3 = false
                total -= 1
                beliTiket(total)
            } else {
                d3.setImageResource(R.drawable.ic_rectangle_selected)
                statusD3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D3", "40000")
                datalist.add(data)
            }
        }

        d4.setOnClickListener {
            if (statusD4) {
                d4.setImageResource(R.drawable.ic_rectangle_empty)
                statusD4 = false
                total -= 1
                beliTiket(total)
            } else {
                d4.setImageResource(R.drawable.ic_rectangle_selected)
                statusD4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D4", "40000")
                datalist.add(data)
            }
        }

        btn_home.setOnClickListener {
            val intent = Intent (this, CheckOutActivity::class.java)
                .putExtra("data", datalist).putExtra("datas", data)
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