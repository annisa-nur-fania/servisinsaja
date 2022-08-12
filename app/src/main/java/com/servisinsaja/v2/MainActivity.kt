package com.servisinsaja.v2

import FormMesinCuciActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.servisinsaja.v2.Form.FormTvActivity
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveDetailTvActivity: ImageButton = findViewById(R.id.btn_tv)
        btnMoveDetailTvActivity.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_tv -> {
                val moveIntent = Intent(this@MainActivity, FormTvActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}