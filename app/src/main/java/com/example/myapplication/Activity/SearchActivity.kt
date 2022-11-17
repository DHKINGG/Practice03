package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
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



        binding.edtSearchHospital.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {

                val nextIntent = Intent(this,SearchResultActivity::class.java)
                nextIntent.putExtra("Search_Keywords",binding.edtSearchHospital.text.toString())
                startActivity(nextIntent)
            }

             true
        }

        binding.ivSearchLeftArrow.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onBackPressed() {  // 검색
        super.onBackPressed()
//        Log.d("back","눌렸음")
        finish()
    }

    private fun setRecommendList(){
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("감기"))
        searchRecommendList.add(SearchRecommendModel("소아청소년과"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))


    }

    private fun setHistoryList(){
        searchHistoryList.add(SearchHistoryModel("금요일진료","11.07"))
        searchHistoryList.add(SearchHistoryModel("토요일진료","10.29"))
        searchHistoryList.add(SearchHistoryModel("독감예방접종","11.29"))
    }
}