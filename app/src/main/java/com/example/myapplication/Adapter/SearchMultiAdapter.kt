package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.HospitalInfoActivity
import com.example.myapplication.Model.CurrentSearchModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.Interface.SetOnClickListenerInterface
import com.example.myapplication.databinding.*
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import timber.log.Timber

class SearchMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    var searchRecommendList = mutableListOf<SearchRecommendModel>()
    var searchHistoryList = mutableListOf<CurrentSearchModel>()
    var searchResultList = mutableListOf<SearchModel>()
    private var onClickListener : SetOnClickListenerInterface? = null

    fun listItemClick(pOnclick: SetOnClickListenerInterface){
        this.onClickListener = pOnclick
    }

    lateinit var textView: TextView

    var isSearch = false


    inner class RecommendHolder(private val binding: IvSearchRecommnedMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SearchRecommendModel>) {
            binding.rvSearchRecommend.layoutManager =
                FlexboxLayoutManager(adapterContext).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW



                }

            val recyclerAdapter = SearchRecommendAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchRecommend.adapter = recyclerAdapter


            // 1. TextView 참조
            textView = binding.tvSearchRecommend
            // 2. String 문자열 데이터 취득
            val textData: String = textView.text.toString()
            // 3. SpannableStringBuilder 타입으로 변환
            val builder = SpannableStringBuilder(textData)
            // 4-3 index=4에 해당하는 문자열(4)에 빨간색 적용
            val colorBlueSpan = ForegroundColorSpan(Color.RED)
            builder.setSpan(colorBlueSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            // 5. TextView에 적용
            textView.text = builder


        }
    }


    inner class ResultHeader2Holder(private val binding: IvSearchResultHeader2MultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

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
            for (i in item) {
                Log.d("vvv", i.hospitalName)

            }
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchResultMulti.adapter = recyclerAdapter


            

        }
    }

    inner class SearchHistoryHolder(private val binding: IvSearchHistoryMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<CurrentSearchModel>) {
            binding.rvSearchHistory.layoutManager = LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = SearchHistoryAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearchHistory.adapter = recyclerAdapter
            onClickListener?.let { recyclerAdapter.listItemClick(it) }



        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (isSearch) {
            when (viewType) {
                0 -> {
                    return ResultHeader2Holder(
                        IvSearchResultHeader2MultiBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }

                1 -> {
                    return ResultHeaderHolder(
                        IvSearchResultHeaderMutilBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
                else -> {
                    return SearchResultHolder(
                        IvSearchResultMultiBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
            }
        } else {
            when (viewType) {
                0 -> {
                    return RecommendHolder(
                        IvSearchRecommnedMultiBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
                else -> {
                    return SearchHistoryHolder(
                        IvSearchHistoryMultiBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        return position
        ///

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Timber.d("getSearch() respons")
        if (isSearch) {
            when (position) {
                0 -> (holder as ResultHeader2Holder).bind()
                1 -> (holder as ResultHeaderHolder).bind()
                else -> (holder as SearchResultHolder).bind(
                    searchResultList
                )
            }
        } else {
            when (position) {
                0 -> (holder as RecommendHolder).bind(searchRecommendList)
                else -> (holder as SearchHistoryHolder).bind(searchHistoryList)
            }

        }
        holder.setIsRecyclable(false)
    }


    override fun getItemCount(): Int {
        return if (isSearch) 3
        else 2
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

    companion object {
        private val TAG = "SearchMultiAdapter"
    }

}

