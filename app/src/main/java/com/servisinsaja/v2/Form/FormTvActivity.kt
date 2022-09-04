package com.servisinsaja.v2.Form

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.servisinsaja.v2.Haversine
import com.servisinsaja.v2.List.ListActivity
import com.servisinsaja.v2.R
import com.servisinsaja.v2.Variable
import com.servisinsaja.v2.databinding.ActivityFormTvBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception


class FormTvActivity : AppCompatActivity(), View.OnClickListener,
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationListener,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    var mLatitude = 0.0
    var mLongtitude = 0.0
    private  lateinit var variabel: Variable



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
    private lateinit var binding: ActivityFormTvBinding



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormTvBinding.inflate(layoutInflater)
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
        Arraymerk!!.add("pilih item")
        Arraymerk!!.add("LG")
        Arraymerk!!.add("Thosiba")
        Arraymerk!!.add("Sharp")
        Arraymerk!!.add("Polytron")

        Arrayperbaikan = ArrayList()
        Arrayperbaikan!!.add("pilih item")
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
                val moveIntent = Intent(this@FormTvActivity, ListActivity::class.java)
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
                LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                //mLatitude = location.latitude.toDouble()
                val currentlat = location.latitude
                val currentlong = location.longitude



                try {
                    Variable.latitude = currentlat
                    Variable.longtitude = currentlong
                }catch (e: Exception){}


                //Toast.makeText(applicationContext, "ini hhhhh"+ Variable.latitude, Toast.LENGTH_SHORT).show()


//                 mLongtitude= location.longitude
////                val jaraknya: Double = Haversine().haversine(currentlat, currentlong, destlat, deslong)
                val currentLatLong = LatLng(location.latitude, location.longitude)
//                Toast.makeText(applicationContext, "ini"+currentlat, Toast.LENGTH_SHORT).show()
//                //placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))

            }
        }
    }

    private fun getMerk(){
        //binding.progressbar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")

        val url = "https://servisinsaja.com/merek.php"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                //binding.progressbar.visibility = View.INVISIBLE
                try {
                    val results = String(responseBody)
                    val respondArray = JSONArray(results)

                Toast.makeText(applicationContext, "Apakah result: "+ results.toString(), Toast.LENGTH_LONG).show()


                } catch (e: Exception) {
                    Log.d("Followers", e.message.toString())
//                    Toast.makeText(
//                        applicationContext,
//                        "Exception" + e.message.toString(),
//                        Toast.LENGTH_LONG
//                    ).show()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                //binding.progressbar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request 4"
                    403 -> "$statusCode : Forbidden 4"
                    404 -> "$statusCode : Not Found 4"
                    else -> "$statusCode : ${error.message}"
                }
                Log.d("onFailure", error.message.toString())
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {
//        mLatitude = location.latitude
//        mLongtitude = location.longitude
//        Toast.makeText(applicationContext, "ini"+mLatitude, Toast.LENGTH_SHORT).show()


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