package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.databinding.IvHospitalInfoTopBinding


class HospitalInfoTopInfAdapter : RecyclerView.Adapter<HospitalInfoTopInfAdapter.Holder>() {

    var list = mutableListOf<HomeBookModel>()
    lateinit var adapterContext: Context

    inner class Holder(private val binding:IvHospitalInfoTopBinding) :  RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeBookModel,position: Int){
        binding.tvHospitalName.text = item.hospitalName
            binding.tvLocation.text = item.hospitalAddress


        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HospitalInfoTopInfAdapter.Holder {
        return  Holder(
            IvHospitalInfoTopBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HospitalInfoTopInfAdapter.Holder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}