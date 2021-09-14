package com.rohmanbeny.mov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    var desc:String ? = "",
    var director:String ? = "",
    var genre:String ? = "",
    var judul:String ? = "",
    var poster:String ? = "",
    var rating:String ? = ""
) : Parcelable