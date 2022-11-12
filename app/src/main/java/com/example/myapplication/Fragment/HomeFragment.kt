package com.example.myapplication.Fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Activity.ApiUrlActivity
import com.example.myapplication.Adapter.HomeMultiAdapter
import com.example.myapplication.Api.HomeBookApi
import com.example.myapplication.Model.*
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private var homeAdData = mutableListOf<HomeAd>()
    private var homeBottomAdData = mutableListOf<HomeBottomAd>()
    private var bookData = mutableListOf<HomeReceipt>()
    private var weekendData = mutableListOf<HomeWeekend>()

    private var homeData = mutableListOf<HomeBookModel>()

    private var adapter = HomeMultiAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val PERMISSION_ID = 42



    override fun initView() {

        setHomeADData()
        setBottomADData()

        binding.rvHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter.bookList = bookData
        adapter.homeAdList = homeAdData
        adapter.homeBottomAdList = homeBottomAdData
        adapter.weekendList = weekendData
        adapter.setContext(requireContext())
        binding.rvHome.adapter = adapter

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getLastLocation()

        getHomeBook()
    }

    private fun getHomeBook(){
        val api = HomeBookApi.create()
        api.getHomeBookApi(ApiUrlActivity.apiKey, "1", "8").enqueue(object : Callback<HospitalModel> {
            override fun onResponse(call: Call<HospitalModel>, response: Response<HospitalModel>) {
                val responseHomeBook = response.body()

                if (responseHomeBook!=null){
                    homeData = responseHomeBook.localDataModel.row

                    adapter.homeBookList = homeData
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<HospitalModel>, t: Throwable) {
                Log.d("data", t.message.toString())
            }
        })
    }


    private fun setHomeADData() {
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))

    }

    private fun setBottomADData(){
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_1))
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_2))
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_3))
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    var location: Location = task.result
                    if (location != null) {
                        Log.d("location", location.latitude.toString())
                        Log.d("location", location.longitude.toString())
                    }
                }
            }
        } else {
            requestPermissions()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }
}