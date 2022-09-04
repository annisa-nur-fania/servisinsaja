package com.servisinsaja.v2.Data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Servis(
    var name: String? = "",
    var alamat: String? = "",
    var jarak: String? = "",
    var photo: Int = 0,
    var isSelected : Boolean = false

) : Parcelable

