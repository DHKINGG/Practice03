package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.databinding.IvHospitalInfoViewPagerBinding

class HospitalInfoTopInfAdapter : RecyclerView.Adapter<HospitalInfoTopInfAdapter.Holder>() {

    var list = mutableListOf<HospitalInfoViewPager>()
    lateinit var adapterContext: Context

    inner class Holder(private val binding:IvHospitalInfoViewPagerBinding) :  RecyclerView.ViewHolder(binding.root){
        fun bind(item:HospitalInfoViewPager,position: Int){



        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HospitalInfoTopInfAdapter.Holder {
        return  Holder(
            IvHospitalInfoViewPagerBinding.inflate(
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