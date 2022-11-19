package com.example.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.databinding.IvSearchHistoryBinding
import com.example.myapplication.databinding.IvSearchResultBinding

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    lateinit var adapterContext: Context
    var list = mutableListOf<SearchModel>()



    inner class Holder(private val binding: IvSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchModel) {

            binding.tvBookHospitalName.text = item.hospitalName
            binding.tvSearchResultHospitalAddress.text = item.hospitalAddress


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultAdapter.Holder {
        return Holder(
            IvSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        Log.d("vvv", list.size.toString())
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}