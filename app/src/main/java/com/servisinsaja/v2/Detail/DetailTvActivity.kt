package com.servisinsaja.v2.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.servisinsaja.v2.Adapter.TvAdapter
import com.servisinsaja.v2.ApiPerbaikan.ApiConfigPerbaikan
import com.servisinsaja.v2.ApiPerbaikan.ResponsePerbaikanItem
import com.servisinsaja.v2.R
import com.servisinsaja.v2.databinding.ActivityDetailTvBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ALAMAT = "extra_alamat"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_JARAK= "extra_jarak"
        const val EXTRA_HARGA= "extra_jarak"

    }
    private lateinit var binding: ActivityDetailTvBinding
    private val listperbaikan = ArrayList<ResponsePerbaikanItem>()
    private  lateinit var rvcheckbox: RecyclerView
    lateinit var adapter: TvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvcheckbox = findViewById(R.id.rv_checkbox)
        showPerbaikan()


        val tvName: TextView = findViewById(R.id.tv_namajasa)
        val tvJarak: TextView = findViewById(R.id.tv_jarak)
        val tvAlamat: TextView = findViewById(R.id.tv_alamat)
        val imgPhoto: ImageView = findViewById(R.id.img_detail)
        val tvResult: TextView = findViewById(R.id.tvresult)


        //inten memanggil data
        val Name = intent.getStringExtra(EXTRA_NAME)
        val jarak = intent.getStringExtra(EXTRA_JARAK)
        val alamat = intent.getStringExtra(EXTRA_ALAMAT)
        val Img = intent.getIntExtra(EXTRA_PHOTO, 0)



        tvName.text = Name
        tvJarak.text = jarak
        tvAlamat.text = alamat
        Glide.with(this)
            .load(Img)
            .apply(RequestOptions())
            .into(imgPhoto)

        adapter = TvAdapter( listperbaikan)

        binding.buttonEtimasi.setOnClickListener {
            val data = adapter.getItem()
            tvResult.text = data.toString()

        }

    }


    private fun showPerbaikan() {
        rvcheckbox.setHasFixedSize(true)
        rvcheckbox.layoutManager = LinearLayoutManager(this)

        ApiConfigPerbaikan.instance.getBengkel(1).enqueue(object:
            Callback<ArrayList<ResponsePerbaikanItem>> {
            override fun onFailure(call: Call<ArrayList<ResponsePerbaikanItem>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<ResponsePerbaikanItem>>,
                response: Response<ArrayList<ResponsePerbaikanItem>>
            ) {
//                val responseCode = response.code().toString()
//                tvResponseCode.text = responseCode
                response.body()?.let { listperbaikan.addAll(it) }
                val adapter = TvAdapter(listperbaikan)
                rvcheckbox.adapter = adapter
            }
        }
        )
    }
}