//package com.servisinsaja.v2.Form
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.location.Criteria
//import android.location.Location
//import android.location.LocationManager
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.ProgressBar
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.servisinsaja.v2.R
import kotlinx.android.synthetic.main.activity_form_tv.*

class FormMesinCuciActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_mesin_cuci)

    }
}