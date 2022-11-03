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
import com.google.android.material.tabs.TabLayoutMediator

class HomeBottomViewPagerAdAdapter:   RecyclerView.Adapter<HomeBottomViewPagerAdAdapter.Holder>() {
    var list = mutableListOf<HomeBottomAd>()


    inner class Holder(private val binding: IvViewPager2Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeBottomAd, position: Int){
            var viewPagerAdapterBottom = ViewPagerAdapter()
            viewPagerAdapterBottom.list = item.adImageList
            binding.vpViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager2.adapter = viewPagerAdapterBottom

            TabLayoutMediator(binding.tlIndicator2, binding.vpViewPager2) {tab, position ->}.attach()
        }
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvViewPager2Binding.inflate(
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