package com.example.myapplication.Activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchMultiAdapter
import com.example.myapplication.Api.SearchApi
import com.example.myapplication.CustomView.RelationCustomView
import com.example.myapplication.Model.*
import com.example.myapplication.Interface.SetOnClickListenerInterface
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





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setRecommendList()


        adapter.listItemClick(object: SetOnClickListenerInterface {
            override fun listItemClickListener(searchKey: String) {
                Log.d("dddd","zzzzz")
                getSearch(searchKey)
            }

        })




        relationCustomView = RelationCustomView(this)

        currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
        if (currentSearchList == null) {currentSearchList = mutableListOf() }



        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.searchRecommendList.addAll(searchRecommendList)
        adapter.searchHistoryList.clear()
        adapter.searchHistoryList.addAll(currentSearchList)
        binding.rvSearch.adapter = adapter






        binding.edtSearchHospital.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {

                animationCustom()
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
            floatingButton()
            binding.edtSearchHospital.text = null
            binding.clRelativeKeywords.visibility = View.GONE
            if (adapter.isSearch) {
                animationCustom()
                adapter.isSearch = false
                adapter.searchHistoryList.clear()
                var currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
                if (currentSearchList == null) { currentSearchList = mutableListOf() }
                adapter.searchHistoryList.addAll(currentSearchList)
                adapter.notifyDataSetChanged()
            } else finish()
        }
        binding.searchResultFab.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }


    }






    private fun getSearch(searchKeyWord: String) {
        val api = SearchApi.create()
        api.getSearchApi(ApiUrlActivity.searchApiKey, "5", "1", searchKeyWord).enqueue(object :
            Callback<ResultCode> {
            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                val responseSearch = response.body()

                if (responseSearch != null) {
                    Log.d("sss", searchKeyWord)
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
    private fun animationCustom(){
        val constraintSet = ConstraintSet()   // 셋
        val constraintLayout = binding.clHeader // 해당  레이아웃에있는 뷰 바인딩해와
        constraintSet.clone(constraintLayout) // 셋으로 컨스레이아웃 클론(바뀔 레이아웃)

        if(adapter.isSearch) binding.llHeader.removeView(relationCustomView)// isSearch 로 뷰 띄울지 여부 판단
        else binding.llHeader.addView(relationCustomView)

        constraintSet.applyTo(constraintLayout)  //적용

        val trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.clHeader, trans) // 애니메이션 효과
    }




}
