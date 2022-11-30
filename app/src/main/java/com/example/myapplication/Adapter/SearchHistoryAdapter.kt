package com.example.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.ApiUrlActivity
import com.example.myapplication.Model.CurrentSearchModel
import com.example.myapplication.Interface.SetOnClickListenerInterface
import com.example.myapplication.databinding.IvSearchHistoryBinding

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.Holder>() {
    private lateinit var adapterContext: Context
    var list = mutableListOf<CurrentSearchModel>()
    private var onClickListener: SetOnClickListenerInterface? = null


    fun listItemClick(pOnclick: SetOnClickListenerInterface) {
        this.onClickListener = pOnclick
    }


    inner class Holder(private val binding: IvSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CurrentSearchModel) {
            binding.tvSearchWord.text = item.searchKeyWords
            binding.tvSearchHistoryDate.text = item.date




            binding.ivSearchDel.setOnClickListener {
                list.removeAt(position)
                notifyDataSetChanged()

                var searchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey) //sp 에 저장된 값 가져와
                if (searchList == null) searchList = mutableListOf()
                searchList.removeAt(position)
                ApiUrlActivity.prefs.setSearchKeyWords(ApiUrlActivity.searchListPrefKey, searchList)
            }
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.clSearchResultHistory.setOnClickListener {
                    Log.d("sss", binding.tvSearchWord.toString())
                    onClickListener?.listItemClickListener(binding.tvSearchWord.text.toString())
                }
            }


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
        return if (list.size > 5) {
            return 5
        } else list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}