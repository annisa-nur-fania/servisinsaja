package com.servisinsaja.v2.List



import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servisinsaja.v2.Adapter.CardViewAdapter
import com.servisinsaja.v2.Api.ApiConfig
import com.servisinsaja.v2.Api.ResponseMorty
import com.servisinsaja.v2.Api.ResponseServis
import com.servisinsaja.v2.Data.Servis
import com.servisinsaja.v2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private lateinit var rvJasaServbis: RecyclerView
    private var list: ArrayList<Servis> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rvJasaServbis = findViewById(R.id.rv_jasaservis)

        ApiConfig.getService().getMorty().enqueue(object : Callback<ResponseServis> {
            override fun onResponse(call: Call<ResponseServis>, response: Response<ResponseServis>){
                if (response.isSuccessful) {
                    val ResponseServis = response.body()
                    val dataServise = ResponseServis?.data
                    val mortyAdapter = CardViewAdapter(dataServise)
                    rvJasaServbis.apply {
                        layoutManager = LinearLayoutManager(this@ListActivity)
                        setHasFixedSize(true)
                        mortyAdapter.notifyDataSetChanged()
                        adapter = mortyAdapter
                    }
                }

            }

            override fun onFailure(call: Call<ResponseServis>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
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

