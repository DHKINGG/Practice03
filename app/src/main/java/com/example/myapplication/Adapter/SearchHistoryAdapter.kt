package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.databinding.IvSearchHistoryBinding

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.Holder>() {
    lateinit var adapterContext: Context
    var list = mutableListOf<SearchHistoryModel>()

    inner class Holder(private val binding: IvSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchHistoryModel) {
            binding.tvSearchWord.text = item.comment
            binding.tvSearchHistoryDate.text = item.data


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryAdapter.Holder {
        return Holder(
            IvSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchHistoryAdapter.Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}