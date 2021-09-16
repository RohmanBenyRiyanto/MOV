package com.rohmanbeny.mov.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.model.Checkout
import com.rohmanbeny.mov.model.Film
import com.rohmanbeny.mov.tiket.TiketActivity
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckOutActivity : AppCompatActivity() {

    private var datalist = ArrayList<Checkout>()
    private var total :Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences (this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in datalist.indices) {
            total += datalist[a].harga!!.toInt()
        }

        datalist.add(Checkout("Total harus dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(datalist) {

        }
        btn_tiket.setOnClickListener {
            val intent = Intent(this,
                SuccesBeliActivity::class.java)
            startActivity(intent)

            if (data != null) {
                showNotif(data)
            }
        }
        btn_home.setOnClickListener {
            finish()
        }

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(datalist) {
        }

        if(preferences.getValues("saldo")!!.isNotEmpty()) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            btn_tiket.visibility = View.VISIBLE
            textView42.visibility = View.INVISIBLE

        } else {
            tv_saldo.setText("Rp 0")
            btn_tiket.visibility = View.INVISIBLE
            textView42.visibility = View.VISIBLE
            textView42.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
                    "untuk melakukan transaksi"
        }
    }

    private fun showNotif(datas: Film) {
        val NOTIFICATION_CHANNEL_ID = "channel_bwa_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "BWAMOV Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this, CheckoutSuccessActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        mIntent.putExtras(bundle)

        val mIntent = Intent(this, TiketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data", datas)
        mIntent.putExtras(bundle)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.logo_notification
                )
            )
            .setTicker("notif bwa starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Terbeli")
            .setContentText("Tiket "+datas.judul+" berhasil kamu dapatkan. Enjoy the movie!")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }
}