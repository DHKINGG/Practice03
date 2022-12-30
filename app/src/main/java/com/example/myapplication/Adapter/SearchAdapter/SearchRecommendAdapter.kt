package com.example.myapplication.Adapter.SearchAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.IvSearchRecommendBinding


class SearchRecommendAdapter : RecyclerView.Adapter<SearchRecommendAdapter.Holder>() {
    private lateinit var adapterContext: Context
    var list = mutableListOf<SearchRecommendModel>()



    inner class Holder(private val binding: IvSearchRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchRecommendModel) {
            binding.tagSearch.text = item.searchWord


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvSearchRecommendBinding.inflate(
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

        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}