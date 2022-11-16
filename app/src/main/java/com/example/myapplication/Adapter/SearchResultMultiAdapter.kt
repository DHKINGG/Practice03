package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.*

class SearchResultMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context

    var searchResultList = mutableListOf<SearchModel>()

    inner class ResultHeaderHolder(private val binding: IvSearchResultHeaderMutilBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {


        }
    }

    inner class SearchResultHolder(private val binding: IvSearchResultMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SearchModel>) {
            binding.rvSearchResultMulti.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = SearchResultAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchResultMulti.adapter = recyclerAdapter
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> { return ResultHeaderHolder(
                IvSearchResultHeaderMutilBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            }else -> { return SearchResultHolder(
            IvSearchResultMultiBinding.inflate(
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
            0 -> (holder as ResultHeaderHolder).bind()
            else -> (holder as SearchResultHolder).bind(searchResultList)
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

