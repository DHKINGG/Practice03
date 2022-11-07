package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeAd
import com.example.myapplication.databinding.IvViewPagerPageBinding


class HomeAdViewPagerAdapter:   RecyclerView.Adapter<HomeAdViewPagerAdapter.Holder>() {
    var list = mutableListOf<HomeAd>()
    lateinit var adapterContext: Context


    inner class Holder(private val binding: IvViewPagerPageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeAd, position: Int){
            binding.ivViewPager.setImageResource(list[position].adImageList)
            binding.ivViewPager.setOnClickListener {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
                adapterContext.startActivity(intent)
            }



        }
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvViewPagerPageBinding.inflate(
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

    fun setContext(context: Context) {
        adapterContext = context
    }
}