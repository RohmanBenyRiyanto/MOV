package com.rohmanbeny.mov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.utils.Preferences
import com.rohmanbeny.mov.wallet.adaptor.WalletAdapter
import com.rohmanbeny.mov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_my_wallet.*
import kotlinx.android.synthetic.main.activity_my_wallet.iv_close
import kotlinx.android.synthetic.main.activity_my_wallet.tv_saldo
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyWalletActivity : AppCompatActivity() {

    private lateinit var preferences : Preferences
    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        preferences = Preferences(this)

        if (!preferences.getValues("saldo").equals("")){
            curency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        } else {
            tv_saldo.setText("Duit ane kosong :(")
        }

        dataList.add(
            Wallet (
                "Spider Man",
                "Sabtu, 12 Des, 2021",
                800000.0,
                "0"
            )
        )
        dataList.add(
            Wallet (
                "Top Up",
                "Sabtu, 12 Des, 2021",
                1800000.0,
                "1"
            )
        )
        dataList.add(
            Wallet (
                "Spider Man",
                "Sabtu, 12 Des, 2021",
                800000.0,
                "0"
            )
        )

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList) {

        }

        btn_top_up.setOnClickListener{
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }

        iv_close.setOnClickListener {
            finish()
        }

    }
    private fun  curency(harga : Double, textView : TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}