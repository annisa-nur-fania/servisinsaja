package com.servisinsaja.v2.Api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

//    @GET("detail/{id}")
//    fun getRestaurant(
//        @Path("id") id: String
//    ): Call<ResponseServis>


//    @GET("bengkel.php")
//    fun getBengkel()
//    //            (@Query("id_kategori") id_kategori: Int)
//    : Call<ResponseServis>

    @GET ("bengkel.php")
    fun getBengkel
                (@Query("id_kategori") id_kategori: Int)

            : Call<ArrayList<ResponseServisItem>>


}