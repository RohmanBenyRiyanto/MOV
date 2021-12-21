package com.rohmanbeny.mov.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.home.dashboard.DashboardFragment
import com.rohmanbeny.mov.setting.SettingFragment
import com.rohmanbeny.mov.tiket.TicketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentSetting = SettingFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.ic_home_active)
            changeIcon(iv_menu2, R.drawable.ic_tiket)
            changeIcon(iv_menu3, R.drawable.ic_profile)
        }
        iv_menu2.setOnClickListener{
            setFragment(fragmentTicket)

            changeIcon(iv_menu1, R.drawable.ic_home)
            changeIcon(iv_menu2, R.drawable.ic_tiket_active)
            changeIcon(iv_menu3, R.drawable.ic_profile)
        }
        iv_menu3.setOnClickListener{
            setFragment(fragmentSetting)

            changeIcon(iv_menu1, R.drawable.ic_home)
            changeIcon(iv_menu2, R.drawable.ic_tiket)
            changeIcon(iv_menu3, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}