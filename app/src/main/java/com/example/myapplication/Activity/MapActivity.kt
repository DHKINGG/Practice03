package com.example.myapplication.Activity

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.Model.SearchModel


import com.example.myapplication.databinding.ActivityMapBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)






        mapView = binding.mapNaver
        mapView.getMapAsync(this)


        val mapBottomInfo = intent.getSerializableExtra("object") as SearchModel

        binding.tvBookHospitalName.text = mapBottomInfo.hospitalName
        binding.tvSearchResultHospitalAddress.text = mapBottomInfo.hospitalAddress
        binding.ivSearchLeftArrow.setOnClickListener {finish()}




    }


    override fun onBackPressed() {
        Log.d("ppp", "실행완료 ")
        super.onBackPressed()
        finish()
    }



    override fun onMapReady(map: com.naver.maps.map.NaverMap) {
        val naverMap = map
        val uiSettings = naverMap.uiSettings

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 5.0


        naverMap.setOnMapClickListener { point, coord ->
            Log.d("QQQQ", "실행완료 ")
            binding.clBottomInfo.visibility = View.GONE
        }


        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.474113,127.110258))
        naverMap.moveCamera(cameraUpdate)

        uiSettings.isLocationButtonEnabled = false
        uiSettings.isZoomControlEnabled = false
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource
        val locationOverlay = naverMap.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.position =LatLng(37.474113,127.110258)

        binding.searchResultFabMap.map = naverMap



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE)
            return

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            if (!locationSource.isActivated){ // 권한 거부시 위치추적 x
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
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



    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }



}


