package com.example.myapplication.Activity


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.databinding.ActivityMapBinding
import com.google.android.gms.location.*
import com.google.type.DateTime
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import java.time.LocalDateTime.now


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0


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
        binding.ivSearchLeftArrow.setOnClickListener { finish() }


    }


    override fun onBackPressed() {
        Log.d("ppp", "실행완료 ")
        super.onBackPressed()
        finish()
    }


    override fun onMapReady(map: com.naver.maps.map.NaverMap) {
        naverMap = map
        val uiSettings = naverMap.uiSettings
        val cameraPosition = CameraPosition(LatLng(37.473645, 127.114391), 16.0)


        naverMap.cameraPosition = cameraPosition
        naverMap.minZoom = 10.0

        naverMap.setOnMapClickListener { point, coord ->
            binding.clBottomInfo.visibility = View.GONE
        }
        uiSettings.isLocationButtonEnabled = false
        uiSettings.isZoomControlEnabled = false
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        binding.searchResultFabMap.setOnClickListener {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
            naverMap.moveCamera(cameraUpdate)

            val locationOverlay = naverMap.locationOverlay
            locationOverlay.isVisible = true
            locationOverlay.position = LatLng(latitude, longitude)
            Log.d("ck", locationOverlay.position.toString())

        }


        mapView.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.clBottomInfo.visibility = View.GONE
                    //터치가 눌리면...
                }
            }
            //리턴값이 false면 seekbar 동작 안됨
            false //or false
        }



        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this) //gps 자동으로 받아오기
        setUpdateLocationListner() //내위치를 가져오는 코드


    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback


    private fun setUpdateLocationListner() {
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY //높은 정확도
            interval = 1000 //1초에 한번씩 GPS 요청
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for ((i, location) in locationResult.locations.withIndex()) {
                    Log.d("location: ", "${location.latitude}, ${location.longitude}")
                    latitude = location.latitude
                    longitude = location.longitude
                    setLastLocation(location)
                }
            }
        }
        //location 요청 함수 호출 (locationRequest, locationCallback)

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
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }//좌표계를 주기적으


    fun setLastLocation(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
        val marker = Marker()
        marker.position = myLocation

        marker.map = naverMap
        //마커
//        val cameraUpdate = CameraUpdate.scrollTo(myLocation)
//        naverMap.moveCamera(cameraUpdate)
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 5.0

        //marker.map = null
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


