package com.todaysquare.retrofitfunctiontestbed.ui.library

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.todaysquare.retrofitfunctiontestbed.R
import com.todaysquare.retrofitfunctiontestbed.data.network.api.SeoulRestApi

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var  mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val seoulRestApi = SeoulRestApi()

        mMap = googleMap

        /*val seoul = LatLng(37.566418, 126.977943)
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(37.566418, 126.977943))
            .zoom(15.0f)
            .build()
        val markerOptions = MarkerOptions()
            .position(seoul)
            .title("This is Seoul")
            .snippet("37.566418, 126.977943")

        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))*/

        seoulRestApi.loadLibraries(this, mMap)
        mMap.setOnMarkerClickListener {
            if (it.tag != null) {
                var url = it.tag as String
                if (!url.startsWith("http"))
                    url = "http://${url}"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                startActivity(intent)
            }
            true

        }
    }
}