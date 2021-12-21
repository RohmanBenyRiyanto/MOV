package com.rohmanbeny.mov.sign.signup

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import com.rohmanbeny.mov.R
import com.rohmanbeny.mov.home.HomeActivity
import com.rohmanbeny.mov.sign.signin.User
import com.rohmanbeny.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.iv_close
import kotlinx.android.synthetic.main.activity_tiket.*
import java.util.*

class SignUpPhotoScreenActivity : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd: Boolean = false
    lateinit var filePath: Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var user : User
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        user = intent.getParcelableExtra("data")!!
        tv_hello.text = "Selamat Datang\n"+user.nama

        iv_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                btn_save.visibility = View.INVISIBLE
                iv_add.setImageResource(R.drawable.ic_btn_upload)
                iv_profile.setImageResource(R.drawable.user_pic)

            } else {
//                Dexter.withActivity(this)
//                    .withPermission(android.Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()

                ImagePicker.with(this)
                    .cameraOnly()	//User can only capture image using Camera
                    .start()
            }
        }

        btn_home.setOnClickListener {
            finishAffinity()

            var goHome = Intent(this@SignUpPhotoScreenActivity,
                HomeActivity::class.java)
            startActivity(goHome)
        }
        iv_close.setOnClickListener {
            finish()
        }

        btn_save.setOnClickListener {
            if (filePath != null){
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preferences.setValues("url", it.toString())
                        }
                        var goHome = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
                        startActivity(goHome)
                    }

                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var  progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload"+progress.toInt()+" %")
                    }

            } else {

            }
        }
    }

    private fun saveToFirebase(url: String) {

        mFirebaseDatabase.child(user.username!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                user.url = url
                mFirebaseDatabase.child(user.username!!).setValue(user)

                preferences.setValues("nama", user.nama.toString())
                preferences.setValues("user", user.username.toString())
                preferences.setValues("saldo", "")
                preferences.setValues("url", "")
                preferences.setValues("email", user.email.toString())
                preferences.setValues("status", "1")
                preferences.setValues("url", url)

                finishAffinity()
                val intent = Intent(this@SignUpPhotoScreenActivity,
                    HomeActivity::class.java)
                startActivity(intent)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpPhotoScreenActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        //To change body of created functions use File | Settings | File Templates.
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }

        ImagePicker.with(this)
            .cameraOnly()	//User can only capture image using Camera
            .start()

    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak dapat menambahkan photo Profile", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Tergesah? klik tombol aploud nanti aja", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: com.karumi.dexter.listener.PermissionRequest?,
        token: PermissionToken?
    ) {
        //To change body of created functions use File | Settings | File Templates.
    }

//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            var bitmap = data?.extras?.get("data") as Bitmap
//            statusAdd = true
//
//            filePath = data.getData()!!
//
//            Glide.with(this)
//                .load(bitmap)
//                .apply(RequestOptions.circleCropTransform())
//                .into(iv_profile)
//
//            btn_save.visibility = View.VISIBLE
//            iv_add.setImageResource(R.drawable.ic_btn_delete)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            statusAdd = true
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            btn_save.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}