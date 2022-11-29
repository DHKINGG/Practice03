package com.example.myapplication.Adapter

import android.R
import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.SearchActivity
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.IvSearchRecommendBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager


class SearchRecommendAdapter : RecyclerView.Adapter<SearchRecommendAdapter.Holder>() {
    private lateinit var adapterContext: Context
    var list = mutableListOf<SearchRecommendModel>()



    inner class Holder(private val binding: IvSearchRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchRecommendModel) {
            binding.tagSearch.text = item.searchWord


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecommendAdapter.Holder {
        return Holder(
            IvSearchRecommendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: SearchRecommendAdapter.Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}