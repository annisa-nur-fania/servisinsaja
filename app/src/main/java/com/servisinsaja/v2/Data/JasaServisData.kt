package com.servisinsaja.v2.Data

import com.servisinsaja.v2.R

object JasaServisData {
    private val jasaName = arrayOf(
        "servisinsaja",
        "servisinsaja"

    )
    private val alamat = arrayOf(
        "alamat",
        "alamat"
    )

    private val Jarak = doubleArrayOf(
        -5.4404774859843865,
        105.28907580987789
    )


    val listData: ArrayList<Servis>
        get() {
            val list = arrayListOf<Servis>()
            for (position in jasaName.indices) {
                val jasaservis = Servis()
                jasaservis.name = jasaName[position]
                jasaservis.alamat = alamat[position]
                jasaservis.jarak = Jarak[position].toString()


                list.add(jasaservis)
            }
            return list
        }

}