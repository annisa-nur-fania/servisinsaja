package com.servisinsaja.v2.ApiPerbaikan

import com.servisinsaja.v2.Api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfigPerbaikan {

    private const val BASE_URL = "https://servisinsaja.com/"

    val instance: ApiServisPerbaikan by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiServisPerbaikan::class.java)

    }
}