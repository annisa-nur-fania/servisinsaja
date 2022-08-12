package com.servisinsaja.v2.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("bengkel.php")
    //"character"
    fun getMorty() : Call<ResponseServis>
}