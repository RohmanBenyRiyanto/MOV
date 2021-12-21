package com.rohmanbeny.mov.tiket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.model.Checkout
import com.rohmanbeny.mov.model.Film
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    private var datalist = arrayListOf<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data!!.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into((iv_poster))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        datalist.add(Checkout("C1", ""))
        datalist.add(Checkout("C2", ""))

        rc_checkout.adapter = TiketAdapter(datalist) {

        }
        iv_close.setOnClickListener {
            finish()
        }

        iv_barcode.setOnClickListener {
            showDialog("Silahkan melakukan scanning pada counter tiket terdekat")
        }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_qr)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        val tvDesc = dialog.findViewById(R.id.tv_desc) as TextView
        tvDesc.text = title

        val btnClose = dialog.findViewById(R.id.btn_close) as Button
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}