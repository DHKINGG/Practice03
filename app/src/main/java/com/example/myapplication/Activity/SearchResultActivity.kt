package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.Model.HospitalModel
import com.example.myapplication.Model.ResultCode
import com.example.myapplication.databinding.ActivitySearchResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // intent로 검색어 가져와서 getSearch호출
        val searchKeyWord2 = intent.getStringExtra("Search_Keywords")
        getSearch(searchKeyWord2.toString())

    }

    private fun getSearch(searchKeyWord: String){
        val api = SearchApi.create()
        api.getSearchApi(ApiUrlActivity.searchApiKey,"1","1", "아").enqueue(object :
            Callback<ResultCode> {
            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                val responseSearch = response.body()

                if (responseSearch!=null){
                    Log.d("data" , responseSearch.resultList[0].hospitalName)
                }
            }

            override fun onFailure(call: Call<ResultCode>, t: Throwable) {
                Log.d("data", t.message.toString())
            }
        })
    }
}