package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.SearchActivity
import com.example.myapplication.databinding.IvHomeHeaderBinding

class HomeMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var adapterContext: Context

    inner class HomeHeaderHolder(val binding: IvHomeHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.clHomeSearch.setOnClickListener {
                val intent = Intent(adapterContext, SearchActivity::class.java)
                adapterContext.startActivity(intent)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return HomeHeaderHolder(
                    IvHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}