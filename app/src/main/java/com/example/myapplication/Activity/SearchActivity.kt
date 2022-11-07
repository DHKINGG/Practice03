package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchMultiAdapter
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private var searchRecommendList = mutableListOf<SearchRecommendModel>()
    private var searchHistoryList = mutableListOf<SearchHistoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setRecommendList()
        setHistoryList()

        val adapter = SearchMultiAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.searchRecommendList = searchRecommendList
        adapter.searchHistoryList = searchHistoryList
        binding.rvSearch.adapter = adapter
    }

    private fun setRecommendList(){
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("감기"))
        searchRecommendList.add(SearchRecommendModel("소아청소년과"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))

    }

    private fun setHistoryList(){
        searchHistoryList.add(SearchHistoryModel("독감예방접종","11.07"))
        searchHistoryList.add(SearchHistoryModel("토요일진료","10.29"))
        searchHistoryList.add(SearchHistoryModel("독감예방접종","11.29"))
        searchHistoryList.add(SearchHistoryModel("독감예방접종","01.07"))
    }
}