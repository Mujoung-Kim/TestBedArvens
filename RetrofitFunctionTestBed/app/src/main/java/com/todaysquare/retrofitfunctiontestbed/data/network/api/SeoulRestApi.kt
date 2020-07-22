package com.todaysquare.retrofitfunctiontestbed.data.network.api

import android.content.Context
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

import com.todaysquare.retrofitfunctiontestbed.data.model.Library
import com.todaysquare.retrofitfunctiontestbed.data.network.SeoulOpenService
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.BASE_SEO
import com.todaysquare.retrofitfunctiontestbed.utils.SPUtil

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeoulRestApi {
    private val seoulOpenService: SeoulOpenService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_SEO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        seoulOpenService = retrofit.create(SeoulOpenService::class.java)

    }

    fun loadLibraries(context: Context, mMap: GoogleMap) {
        seoulOpenService.getLibrary(SPUtil.library_key)
            .enqueue(object : Callback<Library> {
                override fun onFailure(call: Call<Library>, t: Throwable) {
                    Toast.makeText(context, "Could not get data from the server."
                        , Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    showLibraries(response.body() as Library, mMap)

                }
            })

    }

    fun showLibraries(libraries: Library, mMap : GoogleMap) {
        val latLngBounds = LatLngBounds.Builder()

        for (lib in libraries.SeoulPublicLibraryInfo.row) {
            val position = LatLng(lib.XCNTS.toDouble(), lib.YDNTS.toDouble())
            val marker = MarkerOptions().position(position).title(lib.LBRRY_NAME)
            val obj = mMap.addMarker(marker)

            obj.tag = lib.HMPG_URL
            latLngBounds.include(marker.position)

        }

        val bounds = latLngBounds.build()
        val padding = 0
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap.moveCamera(updated)

    }
}