package com.servisinsaja.v2.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JenisMerek(
    var id: String? =null,
    var jenismerek: String? =null,
    var id_jenis : String? =null
): Parcelable
