package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchMultiAdapter
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.Model.ResultCode
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var searchRecommendList = mutableListOf<SearchRecommendModel>()
    private var searchHistoryList = mutableListOf<SearchHistoryModel>()
    private var searchResultData = mutableListOf<SearchModel>()
    private var adapter: SearchMultiAdapter = SearchMultiAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setRecommendList()
        setHistoryList()




        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.searchRecommendList = searchRecommendList
        adapter.searchHistoryList = searchHistoryList
        binding.rvSearch.adapter = adapter



        binding.edtSearchHospital.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                getSearch(binding.edtSearchHospital.text.toString())
            }

            true
        }

        binding.ivSearchLeftArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun getSearch(searchKeyWord: String) {
        val api = SearchApi.create()
        api.getSearchApi(ApiUrlActivity.searchApiKey, "2", "1", searchKeyWord).enqueue(object :
            Callback<ResultCode> {
            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                val responseSearch = response.body()

                if (responseSearch != null) {
                    adapter.isSearch = true
                    searchResultData = responseSearch.resultList
                    adapter.searchResultList = searchResultData
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ResultCode>, t: Throwable) {
                Log.d("data", t.message.toString())
            }
        })
    }


    override fun onBackPressed() {  // 검색
        super.onBackPressed()
//        Log.d("back","눌렸음")
        finish()
    }

    private fun setRecommendList() {
        searchRecommendList.add(SearchRecommendModel("감기"))
        searchRecommendList.add(SearchRecommendModel("감기"))
        searchRecommendList.add(SearchRecommendModel("소아청소년과"))
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))


    }

    private fun setHistoryList() {
        searchHistoryList.add(SearchHistoryModel("금요일진료", "11.07"))
        searchHistoryList.add(SearchHistoryModel("토요일진료", "10.29"))
        searchHistoryList.add(SearchHistoryModel("독감예방접종", "11.29"))
    }
}