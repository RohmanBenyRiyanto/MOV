package com.rohmanbeny.mov.home.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.rohmanbeny.mov.DetailActivity
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.model.Film
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    private lateinit var preferences : Preferences
    private lateinit var mDatabase : DatabaseReference

    private var datalist = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_nama.setText(preferences.getValues("nama"))
        if(preferences.getValues("saldo").equals("")) {
            curenccy(preferences.getValues("saldo")!!.toDouble(),tv_saldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        now_playing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_coming_soon.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                datalist.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    val film = getdataSnapshot.getValue(Film::class.java)
                    datalist.add(film!!)
                }
                now_playing.adapter = NowPlayingAdapter(datalist) {
                    val intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rv_coming_soon.adapter = ComingSoonAdapter(datalist) {
                    val intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "" +databaseError.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun  curenccy(harga : Double, textView : TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}