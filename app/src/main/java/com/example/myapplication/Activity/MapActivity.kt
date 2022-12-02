package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchModel


import com.example.myapplication.databinding.ActivityMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*


class MapActivity : AppCompatActivity(),OnMapReadyCallback  {
    private lateinit var binding: ActivityMapBinding




    private lateinit var mapView: MapView







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mapView = binding.mapNaver
        mapView.getMapAsync(this)





        val mapBottomInfo =  intent.getSerializableExtra("object") as SearchModel

        binding.tvBookHospitalName.text = mapBottomInfo.hospitalName
        binding.tvSearchResultHospitalAddress.text = mapBottomInfo.hospitalAddress
        binding.ivSearchLeftArrow.setOnClickListener {
            finish()
        }


    }


    override fun onStart() {
        super.onStart()

        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: NaverMap) {
      val  naverMap = map

        val uiSettings = naverMap.uiSettings

        uiSettings.isZoomControlEnabled = false
        uiSettings.isLocationButtonEnabled = true
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

    }
}

