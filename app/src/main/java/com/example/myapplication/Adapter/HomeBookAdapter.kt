package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeReceipt
import com.example.myapplication.databinding.IvBookHospitalBinding

class HomeBookAdapter : RecyclerView.Adapter<HomeBookAdapter.Holder>() {

    var list = mutableListOf<HomeReceipt>()
    lateinit var adapterContext: Context

    inner class Holder(private val binding: IvBookHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeReceipt) {
            binding.tvBookHospitalName.text = item.hospitalName
            binding.tvHomeBookHospitalType.text = item.hospitalType
            binding.tvHomeHospitalDistance.text = item.hospitalDistance
            binding.tvHomeBookHospitalAddress.text = item.HospitalLocation
            binding.tvHomeBookHospitalTime1.text = item.waiting
            binding.tvHomeBookHospitalTime2.text = item.waiting2
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