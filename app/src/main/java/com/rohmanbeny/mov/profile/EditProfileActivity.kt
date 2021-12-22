package com.rohmanbeny.mov.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.sign.signin.User
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.iv_close
import kotlinx.android.synthetic.main.activity_pilih_bangku.*
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*

class EditProfileActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {

        preferences = Preferences(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        tv_nama.text = preferences.getValues("nama")
        tv_username.text = preferences.getValues("user")
        tv_password.text = preferences.getValues("password")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile_edit)

        iv_close.setOnClickListener {
            finish()
        }
    }
}