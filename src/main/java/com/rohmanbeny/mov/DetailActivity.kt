package com.rohmanbeny.mov

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.rohmanbeny.mov.checkout.PilihBangkuActivity
import com.rohmanbeny.mov.home.dashboard.PlaysAdapter
import com.rohmanbeny.mov.model.Film
import com.rohmanbeny.mov.model.Plays
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.iv_close
import kotlinx.android.synthetic.main.activity_detail.iv_poster
import kotlinx.android.synthetic.main.activity_detail.tv_genre
import kotlinx.android.synthetic.main.activity_detail.tv_rate
import kotlinx.android.synthetic.main.activity_tiket.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var datalist = ArrayList<Plays> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.judul.toString())
            .child("play")

        tv_kursi.text = data.judul
        tv_genre.text = data.genre
        tv_desc.text = data.desc
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster)

        rv_who_play.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener{
            var intent = Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }
        iv_close.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                datalist.clear()

                for (getdataSnapshot in p0.children) {
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    datalist.add(Film!!)
                }
                rv_who_play.adapter = PlaysAdapter(datalist){

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@DetailActivity,""+p0.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}