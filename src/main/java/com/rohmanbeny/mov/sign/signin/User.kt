package com.rohmanbeny.mov.sign.signin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var email: String ?="",
    var nama: String ?="",
    var password: String ?="",
    var url: String ?="",
    var username: String ?="",
    var saldo: String ?=""
) : Parcelable