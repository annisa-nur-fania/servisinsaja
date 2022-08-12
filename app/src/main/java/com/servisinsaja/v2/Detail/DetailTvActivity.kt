package com.servisinsaja.v2.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.servisinsaja.v2.R
import com.servisinsaja.v2.databinding.ActivityDetailTvBinding

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ALAMAT = "extra_alamat"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_JARAK= "extra_jarak"

    }
    private lateinit var binding: ActivityDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvName: TextView = findViewById(R.id.tv_namajasa)
        val tvJarak: TextView = findViewById(R.id.tv_jarak)
        val tvAlamat: TextView = findViewById(R.id.tv_alamat)
        val imgPhoto: ImageView = findViewById(R.id.img_detail)

        //inten memanggil data
        val Name = intent.getStringExtra(EXTRA_NAME)
        val jarak = intent.getStringExtra(EXTRA_JARAK) + " KM"
        val alamat = intent.getStringExtra(EXTRA_ALAMAT)
        val Img = intent.getIntExtra(EXTRA_PHOTO, 0)


        tvName.text = Name
        tvJarak.text = jarak
        tvAlamat.text = alamat
        Glide.with(this)
            .load(Img)
            .apply(RequestOptions())
            .into(imgPhoto)


    }
}