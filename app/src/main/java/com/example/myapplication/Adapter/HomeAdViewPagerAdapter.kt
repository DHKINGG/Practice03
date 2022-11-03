package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HomeAd
import com.example.myapplication.databinding.IvViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeAdViewPagerAdapter:   RecyclerView.Adapter<HomeAdViewPagerAdapter.Holder>() {
    var list = mutableListOf<HomeAd>()


    inner class Holder(private val binding: IvViewPagerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeAd, position: Int){
            var viewPagerAdapter = ViewPagerAdapter()
            viewPagerAdapter.list = item.adImageList
            binding.vpViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager.adapter = viewPagerAdapter

            TabLayoutMediator(binding.tlIndicator, binding.vpViewPager) {tab, position ->}.attach()
        }
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvViewPagerBinding.inflate(
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