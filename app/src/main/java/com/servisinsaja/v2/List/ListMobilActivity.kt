package com.servisinsaja.v2.List

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servisinsaja.v2.Adapter.CardViewAdapter
import com.servisinsaja.v2.Api.ApiConfig
import com.servisinsaja.v2.Api.ResponseServisItem
import com.servisinsaja.v2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMobilActivity : AppCompatActivity() {
    private lateinit var rvJasaServbis: RecyclerView
    //private var list: ArrayList<Servis> = arrayListOf()
    private val list = ArrayList<ResponseServisItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_mobil)

        rvJasaServbis = findViewById(R.id.rv_jasaservis)

        showServis()
    }

    private fun showServis() {
        rvJasaServbis.setHasFixedSize(true)
        rvJasaServbis.layoutManager = LinearLayoutManager(this)

        ApiConfig.instance.getBengkel(3).enqueue(object: Callback<ArrayList<ResponseServisItem>> {
            override fun onFailure(call: Call<ArrayList<ResponseServisItem>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<ResponseServisItem>>,
                response: Response<ArrayList<ResponseServisItem>>
            ) {
//                val responseCode = response.code().toString()
//                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = CardViewAdapter(list)
                rvJasaServbis.adapter = adapter
            }
        }
        )
    }

}