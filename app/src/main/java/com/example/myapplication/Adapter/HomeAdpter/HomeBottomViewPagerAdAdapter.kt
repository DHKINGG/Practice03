package com.example.myapplication.Adapter.HomeAdpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeBottomAd
import com.example.myapplication.databinding.IvViewPagerPage2Binding

class HomeBottomViewPagerAdAdapter:   RecyclerView.Adapter<HomeBottomViewPagerAdAdapter.Holder>() {
    var list = mutableListOf<HomeBottomAd>()


    inner class Holder(private val binding: IvViewPagerPage2Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: HomeBottomAd, position: Int){
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