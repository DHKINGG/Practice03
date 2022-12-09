package com.example.myapplication.Activity


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.databinding.ActivityMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationSource: FusedLocationSource


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        mapView = binding.mapNaver
        mapView.getMapAsync(this)


        val mapBottomInfo = intent.getSerializableExtra("object") as SearchModel

        binding.tvBookHospitalName.text = mapBottomInfo.hospitalName
        binding.tvSearchResultHospitalAddress.text = mapBottomInfo.hospitalAddress
        binding.ivSearchLeftArrow.setOnClickListener { finish() }


    }


    override fun onBackPressed() {
        Log.d("ppp", "실행완료 ")
        super.onBackPressed()
        finish()
    }


    override fun onMapReady(map: com.naver.maps.map.NaverMap) {
        val naverMap = map
        val uiSettings = naverMap.uiSettings

        naverMap.minZoom = 10.0




        naverMap.setOnMapClickListener { point, coord ->
            binding.clBottomInfo.visibility = View.GONE
        }

        uiSettings.isLocationButtonEnabled = false
        uiSettings.isZoomControlEnabled = false
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource


        var currentLocation: Location?



        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            currentLocation = location

            naverMap.locationOverlay.run {
                isVisible = true
                position = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
            }

            val cameraUpdate = CameraUpdate.scrollTo(
                LatLng(
                    currentLocation!!.latitude,
                    currentLocation!!.longitude
                )
            )
            naverMap.moveCamera(cameraUpdate)

            val cameraUpdateToSeoul =
                CameraUpdate.scrollTo(LatLng(37.47549389999994, 127.10558120000012))

            binding.searchResultFabMap.setOnClickListener {
                naverMap.moveCamera(cameraUpdateToSeoul)
            }

            val marker = Marker()
            marker.position = LatLng(
                naverMap.cameraPosition.target.latitude,
                naverMap.cameraPosition.target.longitude
            )
            marker.map = naverMap
            val marker2 = Marker()
            val marker3 = Marker()
            val marker4 = Marker()
            val marker5 = Marker()

            marker2.position = LatLng(37.47545880000004, 127.10067899999981)
            marker3.position = LatLng(37.473568699999575, 127.1036674999995)
            marker4.position = LatLng(37.47323779999989, 127.10212769999973)
            marker5.position = LatLng(37.47415849999969, 127.11026200000008)

            marker2.map = naverMap
            marker3.map = naverMap
            marker4.map = naverMap
            marker5.map = naverMap


        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE)
            return

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부시 위치추적 x
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


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }


}


