package com.rohmanbeny.mov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_my_wallet.btn_top_up
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.iv_close
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.tv_saldo
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*

class MyWalletTopUpActivity : AppCompatActivity() {

    private lateinit var preferences : Preferences

    private var status1k : Boolean = false
    private var status2k : Boolean = false
    private var status3k : Boolean = false
    private var status4k : Boolean = false
    private var status5k : Boolean = false
    private var status6k : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        preferences = Preferences(this)

        if (!preferences.getValues("saldo").equals("")){
            curency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        } else {
            tv_saldo.setText("Duit ane kosong :(")
        }

        btn_top_up.setOnClickListener{
            startActivity(Intent(this, SuccesTopUpActivity::class.java))
        }

        iv_close.setOnClickListener {
            finish()
        }

        tv_1k.setOnClickListener {
            if(status1k) {
                deselectMoney1(tv_1k)
            } else {
                selectMoney1(tv_1k)
            }
        }
        tv_2k.setOnClickListener {
            if(status2k) {
                deselectMoney1(tv_2k)
            } else {
                selectMoney1(tv_2k)
            }
        }
        tv_3k.setOnClickListener {
            if(status3k) {
                deselectMoney1(tv_3k)
            } else {
                selectMoney1(tv_3k)
            }
        }
        tv_4k.setOnClickListener {
            if(status4k) {
                deselectMoney1(tv_4k)
            } else {
                selectMoney1(tv_4k)
            }
        }
        tv_5k.setOnClickListener {
            if(status5k) {
                deselectMoney1(tv_5k)
            } else {
                selectMoney1(tv_5k)
            }
        }
        tv_6k.setOnClickListener {
            if(status6k) {
                deselectMoney1(tv_6k)
            } else {
                selectMoney1(tv_6k)
            }
        }
    }

    private fun selectMoney1(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status1k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney1(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status1k = false
    }
    private fun selectMoney2(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status2k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney2(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status2k = false
    }
    private fun selectMoney3(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status3k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney3(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status3k = false
    }
    private fun selectMoney4(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status4k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney4(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status4k = false
    }
    private fun selectMoney5(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status5k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney5(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status5k = false
    }

    private fun selectMoney6(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorBlue))
        textView.setBackgroundResource(R.drawable.shape_line_blue)
        status6k = true

        btn_top_up.visibility = View.VISIBLE
    }

    private fun deselectMoney6(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status6k = false
    }

    private fun  curency(harga : Double, textView : TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}