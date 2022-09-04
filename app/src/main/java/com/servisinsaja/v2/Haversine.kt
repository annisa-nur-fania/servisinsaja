package com.servisinsaja.v2



import kotlin.Double


class Haversine  {
    companion object {
        const val R: Double = 6372.8
    }
    fun haversine(latitude1: Double, longitude1: Double, latitude2: Double, longitude2: Double): Double {

        // LOKASI AWAL GEO
        val currentlat = latitude1
        val currentlon = longitude1
        val destinationlat = latitude2
        val destinationlon = longitude2

        // MENCARI DISTANCE
        val latDistance = Math.toRadians(destinationlat - currentlat)
        val lonDistance = Math.toRadians(destinationlon - currentlon)

        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(currentlat)) * Math.cos(Math.toRadians(destinationlat)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val distance: Double = R * c
        return distance
    }
}