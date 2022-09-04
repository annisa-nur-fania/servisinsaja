package com.servisinsaja.v2.List


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servisinsaja.v2.Adapter.CardViewAdapter
import com.servisinsaja.v2.Adapter.TvAdapter
import com.servisinsaja.v2.Api.ApiConfig
import com.servisinsaja.v2.Api.ResponseServisItem
import com.servisinsaja.v2.ApiPerbaikan.ApiConfigPerbaikan
import com.servisinsaja.v2.ApiPerbaikan.ResponsePerbaikanItem
import com.servisinsaja.v2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private lateinit var rvJasaServbis: RecyclerView


    //private var list: ArrayList<Servis> = arrayListOf()
    private val list = ArrayList<ResponseServisItem>()
    private val listperbaikan = ArrayList<ResponsePerbaikanItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rvJasaServbis = findViewById(R.id.rv_jasaservis)


        showServis()

//        ApiConfig.getService().getBengkel().enqueue(object : Callback<ResponseServis> {
//            override fun onResponse(call: Call<ResponseServis>, response: Response<ResponseServis>){
//                if (response.isSuccessful) {
//                    val responseMorty = response.body()
//                    val dataMorty = responseMorty?.response
//                    val mortyAdapter = CardViewAdapter(dataMorty)
//                    rvJasaServbis.apply {
//                        layoutManager = LinearLayoutManager(this@ListActivity)
//                        setHasFixedSize(true)
//                        mortyAdapter.notifyDataSetChanged()
//                        adapter = mortyAdapter
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseServis>, t: Throwable) {
//                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//        })


    }
    private fun showServis() {
        rvJasaServbis.setHasFixedSize(true)
        rvJasaServbis.layoutManager = LinearLayoutManager(this)

        ApiConfig.instance.getBengkel(1).enqueue(object: Callback<ArrayList<ResponseServisItem>>{
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





//        rvJasaServbis.setHasFixedSize(true)
//        list.addAll(JasaServisData.listData)
//        showRecyclerList()

//    private fun showRecyclerList() {
//        rvJasaServbis.layoutManager = LinearLayoutManager(this)
//        val cardViewAdapter = CardViewAdapter(list)
//        rvJasaServbis.adapter = cardViewAdapter
//    }

}

