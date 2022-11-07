package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.IvSearchHistoryMultiBinding
import com.example.myapplication.databinding.IvSearchRecommnedMultiBinding

class SearchMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    var searchRecommendList = mutableListOf<SearchRecommendModel>()
    var searchHistoryList = mutableListOf<SearchHistoryModel>()


    inner class RecommendHolder(private val binding: IvSearchRecommnedMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SearchRecommendModel>) {
            binding.rvSearchRecommend.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = SearchRecommendAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchRecommend.adapter = recyclerAdapter

        }
    }

    inner class SearchHistoryHolder(private val binding: IvSearchHistoryMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SearchHistoryModel>) {
            binding.rvSearchHistory.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = SearchHistoryAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchHistory.adapter = recyclerAdapter
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> { return RecommendHolder(
                    IvSearchRecommnedMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }else -> { return SearchHistoryHolder(
                    IvSearchHistoryMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

        override fun getItemViewType(position: Int): Int {
            return position
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as RecommendHolder).bind(searchRecommendList)
            else -> (holder as SearchHistoryHolder).bind(searchHistoryList)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 2
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}

