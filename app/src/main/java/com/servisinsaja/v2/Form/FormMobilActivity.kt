package com.servisinsaja.v2.Form

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.servisinsaja.v2.List.ListActivity
import com.servisinsaja.v2.List.ListMobilActivity
import com.servisinsaja.v2.R
import com.servisinsaja.v2.databinding.ActivityFormMobilBinding
import com.servisinsaja.v2.databinding.ActivityFormTvBinding

class FormMobilActivity : AppCompatActivity(), View.OnClickListener,
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationListener,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private val TAG = FormTvActivity::class.java.simpleName
        const val LOCATION_REQUEST_CODE = 1
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGTITUDE = "extra_longtitude"
    }

    private var spinnerm: Spinner? = null
    private var spinnern: Spinner? = null
    private var Arraymerk: ArrayList<String>? = null
    private var Arrayperbaikan: ArrayList<String>? = null
    private lateinit var binding: ActivityFormMobilBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Maps
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    // No location access granted.
                }
            }
        }
        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //spinner
        spinnerm = findViewById<Spinner>(R.id.merk_spinner)
        spinnern = findViewById<Spinner>(R.id.jenis_spinner)

        Arraymerk = ArrayList()
        //!!.getdatatv()
        Arraymerk!!.add("pilih jenis merk")
        Arraymerk!!.add("LG")
        Arraymerk!!.add("Thosiba")
        Arraymerk!!.add("Sharp")
        Arraymerk!!.add("Polytron")

        Arrayperbaikan = ArrayList()
        Arrayperbaikan!!.add("pilih jenis perbaikan")
        Arrayperbaikan!!.add("gambar gelap")
        Arrayperbaikan!!.add("layar bergaris")
        Arrayperbaikan!!.add("mati total")
        Arrayperbaikan!!.add("sound mati")
        Arrayperbaikan!!.add("tombol tidak berfungsi")

        val adaptermerk = ArrayAdapter(
            getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
            Arraymerk!!
        )

        val adapterjenis = ArrayAdapter(
            getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
            Arrayperbaikan!!
        )

        adaptermerk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterjenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerm!!.adapter = adaptermerk
        spinnern!!.adapter = adapterjenis
        spinnerm!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                //Toast.makeText(getApplicationContext(), adaptermerk.getItem(i), Toast.LENGTH_SHORT)
                // .show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnern!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                //Toast.makeText(getApplicationContext(), adaptermerk.getItem(i), Toast.LENGTH_SHORT)
                // .show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }


        //intent
        val btnMoveListTvActivity: Button = findViewById(R.id.btn_cari)
        btnMoveListTvActivity.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_cari -> {
                val moveIntent = Intent(this@FormMobilActivity, ListMobilActivity::class.java)

                startActivity(moveIntent)
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMapClickListener { this }
        setUpMap()
        p0.setOnMapClickListener { this }
    }


    fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                FormTvActivity.LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                //mLatitude = location.latitude.toDouble()
                //
                // mLongtitude= location.longitude
                //val jaraknya: Double = Haversine().haversine(currentlat, currentlong, destlat, deslong)
                val currentLatLong = LatLng(location.latitude, location.longitude)
                //Toast.makeText(applicationContext, "ini"+currentlat, Toast.LENGTH_SHORT).show()
                //placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))

            }
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(p0: Location) {
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
}