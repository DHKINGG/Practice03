package com.example.myapplication.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.R
import com.example.myapplication.databinding.IvSearchHistoryMultiBinding
import com.example.myapplication.databinding.IvSearchRecommnedMultiBinding

class SearchMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    var searchRecommendList = mutableListOf<SearchRecommendModel>()
    var searchHistoryList = mutableListOf<SearchHistoryModel>()

    lateinit var textView: TextView

    inner class RecommendHolder(private val binding: IvSearchRecommnedMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SearchRecommendModel>) {
            binding.rvSearchRecommend.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
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

//            // 4-1. index=0 에 해당하는 문자열(0)에 볼드체적용
//            val boldSpan = StyleSpan(Typeface.BOLD)
//            builder.setSpan(boldSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//            // 4-2 index=2에 해당하는 문자열(2)에 글자크기 3배 적용
//            val sizeBigSpan = RelativeSizeSpan(3.0f)
//            builder.setSpan(sizeBigSpan, 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // 4-3 index=4에 해당하는 문자열(4)에 빨간색 적용
            val colorBlueSpan = ForegroundColorSpan(Color.RED)
            builder.setSpan(colorBlueSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // 5. TextView에 적용
            textView.text = builder






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

