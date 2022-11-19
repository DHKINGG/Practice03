package com.example.myapplication.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchMultiAdapter
import com.example.myapplication.Adapter.SearchRecommendAdapter
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.Model.ResultCode
import com.example.myapplication.Model.SearchHistoryModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.Model.SearchRecommendModel
import com.example.myapplication.databinding.ActivitySearchBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
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
        adapter.searchRecommendList.addAll(searchRecommendList)
        adapter.searchHistoryList.addAll(searchHistoryList)
        binding.rvSearch.adapter = adapter



        binding.edtSearchHospital.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                getSearch(binding.edtSearchHospital.text.toString())
                binding.edtSearchHospital.hideKeyboard()

            }

            true
        }

        binding.ivSearchLeftArrow.setOnClickListener {

            if (adapter.isSearch) {
                val intent = Intent(this, SearchActivity::class.java)
                this.startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
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

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


    override fun onBackPressed() {  // 검색
        super.onBackPressed()
//        Log.d("back","눌렸음")
        finish()
    }

    private fun setRecommendList() {
        searchRecommendList.add(SearchRecommendModel("독감예방접종"))
        searchRecommendList.add(SearchRecommendModel("감기"))
        searchRecommendList.add(SearchRecommendModel("소아청소년과"))
        searchRecommendList.add(SearchRecommendModel("이비인후과"))
        searchRecommendList.add(SearchRecommendModel("안과"))
        searchRecommendList.add(SearchRecommendModel("내과"))
        searchRecommendList.add(SearchRecommendModel("영유아검진"))
        searchRecommendList.add(SearchRecommendModel("원스톱진료기관"))
        searchRecommendList.add(SearchRecommendModel("신속항원검사"))
        searchRecommendList.add(SearchRecommendModel("PCR검사"))
        searchRecommendList.add(SearchRecommendModel("호흡기환자진료센터"))


    }

    private fun setHistoryList() {
        searchHistoryList.add(SearchHistoryModel("금요일진료", "11.07"))
        searchHistoryList.add(SearchHistoryModel("토요일진료", "10.29"))
        searchHistoryList.add(SearchHistoryModel("독감예방접종", "11.29"))
    }
}