package com.servisinsaja.v2.ApiPerbaikan

import com.servisinsaja.v2.Api.ResponseServisItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServisPerbaikan {
    @GET("perbaikan.php")
    fun getBengkel
                (@Query("id_jenis") id_kategori: Int)

            : Call<ArrayList<ResponsePerbaikanItem>>
}