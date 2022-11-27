package com.example.myapplication.Activity


import android.content.Context
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchMultiAdapter
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.Model.*
import com.example.myapplication.View.RelationCustomView
import com.example.myapplication.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var searchRecommendList = mutableListOf<SearchRecommendModel>()
    private var currentSearchList = mutableListOf<CurrentSearchModel>()
    private var searchResultData = mutableListOf<SearchModel>()
    private var adapter: SearchMultiAdapter = SearchMultiAdapter()
    private lateinit var relationCustomView: RelationCustomView

    fun sibalsarki() {
        val constraintSet = ConstraintSet()
        val constraintLayout = binding.clHeader

        constraintSet.clone(constraintLayout)

        if (adapter.isSearch) {
            binding.llHeader.removeView(relationCustomView)
        } else {
            binding.llHeader.addView(relationCustomView)
        }

        constraintSet.applyTo(constraintLayout)

        val trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.clHeader, trans)
    }

    fun test(visibility: Int) {
        val constraintSet = ConstraintSet()
        val constraintLayout = binding.clHeader

        constraintSet.clone(constraintLayout)


        constraintSet.applyTo(constraintLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setRecommendList()

        relationCustomView = RelationCustomView(this)







        currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
        if (currentSearchList == null) {
            currentSearchList = mutableListOf()

        }



        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.searchRecommendList.addAll(searchRecommendList)
        adapter.searchHistoryList.clear()
        adapter.searchHistoryList.addAll(currentSearchList)
        binding.rvSearch.adapter = adapter





        binding.edtSearchHospital.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {

                sibalsarki()

                getSearch(binding.edtSearchHospital.text.toString())
                binding.edtSearchHospital.hideKeyboard()
                var currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
                if (currentSearchList == null) {currentSearchList = mutableListOf()}
                currentSearchList.add(CurrentSearchModel(binding.edtSearchHospital.text.toString(), ""))
                ApiUrlActivity.prefs.setSearchKeyWords(ApiUrlActivity.searchListPrefKey, currentSearchList)

                floatingButton()

            }
            true
        }






        binding.ivSearchLeftArrow.setOnClickListener {
//            binding.clRelativeKeywords.visibility = View.GONE
            if (adapter.isSearch) {
                sibalsarki()
                adapter.isSearch = false
                adapter.searchHistoryList.clear()
                var currentSearchList =
                    ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
                if (currentSearchList == null) {
                    currentSearchList = mutableListOf()
                }
                adapter.searchHistoryList.addAll(currentSearchList)
                adapter.notifyDataSetChanged()
            } else finish()
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
                    adapter?.searchResultList = searchResultData
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<ResultCode>, t: Throwable) {
                Log.d("data", t.message.toString())
            }
        })
    }


    private fun floatingButton() {
        if (binding.searchResultFab.visibility == View.VISIBLE)
            binding.searchResultFab.visibility = View.GONE
        else binding.searchResultFab.visibility = View.VISIBLE
    }


    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


    override fun onBackPressed() {
        super.onBackPressed()
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


}
