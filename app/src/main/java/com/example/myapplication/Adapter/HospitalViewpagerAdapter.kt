package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.IvHospitalViewPagerBinding

class HospitalViewpagerAdapter: RecyclerView.Adapter<HospitalViewpagerAdapter.Holder>() {

    var list = mutableListOf<HospitalInfoViewPager>()
    lateinit var adapterContext: Context

    inner class Holder(private val binding: IvHospitalViewPagerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:HospitalInfoViewPager,position: Int){
            binding.ivViewPager.setImageResource(list[position].topImage)
        }
    }





    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):Holder {
        return Holder(
            IvHospitalViewPagerBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder , position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setContext(context: Context) {
        adapterContext = context
    }

}