package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.IvViewPagerPage2Binding

class ViewPagerAdapterBottom :RecyclerView.Adapter<ViewPagerAdapterBottom.PagerViewHolder2>(){
    private val list = mutableListOf<Int>()

    inner class PagerViewHolder2(private val binding: IvViewPagerPage2Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Int) {
            binding.ivViewPager2.setImageResource(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder2 {
        return PagerViewHolder2(
            IvViewPagerPage2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder2, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}