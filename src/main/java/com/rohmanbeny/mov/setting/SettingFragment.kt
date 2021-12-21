package com.rohmanbeny.mov.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.sign.signin.SignInActivity
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.iv_profile

class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())

        tv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)


        btn_keluar.setOnClickListener {
            preferences.clear()
            showMessage("Keluar")
            moveIntent()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(context, SignInActivity::class.java))
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}