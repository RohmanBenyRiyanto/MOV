package com.rohmanbeny.mov.wallet.adaptor

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.wallet.model.Wallet
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class WalletAdapter(private var data: ArrayList<Wallet>,
                    private val listener:(Wallet) -> Unit)
    : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalletAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_transaksi, parent, false
        )
        return ViewHolder (inflatedView)
    }

    override fun onBindViewHolder(holder: WalletAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view :  View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_movie)
        private val tvSub: TextView = view.findViewById(R.id.tv_date)
        private val tvMoney: TextView = view.findViewById(R.id.tv_money)

        fun bindItem(data:Wallet, listener: (Wallet) -> Unit, context : Context) {
            tvTitle.text = data.title
            tvSub.text = data.date

            val localID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)

            if (data.status.equals("0")) {
                tvMoney.text= ("-" + formatRupiah.format(data.money))
                tvMoney.setTextColor(Color.RED)
            } else {
                tvMoney.text= ("+" + formatRupiah.format(data.money))
                tvMoney.setTextColor(Color.GREEN)
            }

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

}
