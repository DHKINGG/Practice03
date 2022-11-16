package com.example.myapplication.Adapter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.databinding.IvBookHospitalBinding
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.*

class HomeBookAdapter : RecyclerView.Adapter<HomeBookAdapter.Holder>() {

    var list = mutableListOf<HomeBookModel>()
    lateinit var adapterContext: Context
    lateinit var location: Location

    inner class Holder(private val binding: IvBookHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var locationManager: LocationManager
        private val locationPermissionCode = 2

        fun bind(item: HomeBookModel) {
            binding.tvBookHospitalName.text = item.hospitalName
            binding.tvHomeBookHospitalType.text = item.hospitalType
//            binding.tvHomeHospitalDistance.text = item.hospitalDistance
//            binding.tvHomeBookHospitalAddress.text = item.hospitalAddress
//            binding.tvHomeBookHospitalTime1.text = item.waiting
//            binding.tvHomeBookHospitalTime2.text = item.waiting2


        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBookAdapter.Holder {
        return Holder(
            IvBookHospitalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}