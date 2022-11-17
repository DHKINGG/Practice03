package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchResultMultiAdapter
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.Model.ResultCode
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.databinding.ActivitySearchResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchResultBinding
    private var searchResultData = mutableListOf<SearchModel>()
    private  var adapter: SearchResultMultiAdapter = SearchResultMultiAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.rvSearchResult.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.searchResultList = searchResultData
        binding.rvSearchResult.adapter = adapter

        binding.ivSearchResultDeleteIcon.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            this.startActivity(intent)
        }

        // intent로 검색어 가져와서 getSearch호출
        val searchKeyWord2 = intent.getStringExtra("Search_Keywords")
        getSearch(searchKeyWord2.toString())

        binding.ivSearchLeftArrow.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            this.startActivity(intent)
        }

    }



    private fun getSearch(searchKeyWord: String){
        val api = SearchApi.create()
        api.getSearchApi(ApiUrlActivity.searchApiKey,"1","1", searchKeyWord).enqueue(object :
            Callback<ResultCode> {
            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                val responseSearch = response.body()

                if (responseSearch!=null){

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
}