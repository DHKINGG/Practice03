package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HomeAd
import com.example.myapplication.Model.HomeBottomAd
import com.example.myapplication.Model.HomeWeekend
import com.example.myapplication.databinding.IvViewPager2Binding
import com.example.myapplication.databinding.IvViewPagerBinding
import com.example.myapplication.databinding.IvViewPagerPage2Binding
import com.google.android.material.tabs.TabLayoutMediator

class HomeBottomViewPagerAdAdapter:   RecyclerView.Adapter<HomeBottomViewPagerAdAdapter.Holder>() {
    var list = mutableListOf<HomeBottomAd>()


    inner class Holder(private val binding: IvViewPagerPage2Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeBottomAd, position: Int){
            binding.ivViewPager2.setImageResource(list[position].adImageList)
        }
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvViewPagerPage2Binding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}