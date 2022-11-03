package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.IvViewPagerPageBinding

class ViewPagerAdapter :RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    var list = mutableListOf<Int>()

    inner class PagerViewHolder(var binding: IvViewPagerPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.ivViewPager.setImageResource(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            IvViewPagerPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}