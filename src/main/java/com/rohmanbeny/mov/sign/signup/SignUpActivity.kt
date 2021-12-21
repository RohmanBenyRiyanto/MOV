package com.rohmanbeny.mov.sign.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.sign.signin.User
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.iv_close
import kotlinx.android.synthetic.main.activity_tiket.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String
    lateinit var sSaldo:String

    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mDatabaseReference: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        iv_close.setOnClickListener {
            finish()
        }

        btn_lanjutkan.setOnClickListener{
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()
            sSaldo = "200000"

            if(sUsername.equals("")){
                et_username.error = "Username wajib diisi"
                et_username.requestFocus()
            }else if(sPassword.equals("")){
                et_password.error = "Password wajib diisi"
                et_password.requestFocus()
            }else if(sNama.equals("")){
                et_nama.error = "Nama wajib diisi"
                et_nama.requestFocus()
            }else if(sEmail.equals("")){
                et_email.error = "Email wajib diisi"
                et_email.requestFocus()
            } else {

                var statusUsername = sUsername.indexOf(".")
                if (statusUsername >=0) {
                    et_username.error = "Silahkan tulis Username Anda tanpa ."
                    et_username.requestFocus()
                } else {
                    saveUser(sUsername, sPassword, sNama, sEmail)
                }

            }
        }
    }

    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String) {

        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkUsername(sUsername, user)

        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String, sSaldo: String) {
        val user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama
        user.email = sEmail
        user.saldo = sSaldo

        if(sUsername != null){
            checkUsername(sUsername, user)
        }
    }

    private fun checkUsername(iUsername: String, data: User) {
        mDatabaseReference.child(iUsername).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot.getValue(User::class.java)
                if(user == null){
                    mDatabaseReference.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val signUp2 = Intent(this@SignUpActivity,
                        SignUpPhotoScreenActivity::class.java).putExtra( "data", data)
                    startActivity(signUp2)
                } else{
                    Toast.makeText(this@SignUpActivity, "Username Telah digunakan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}