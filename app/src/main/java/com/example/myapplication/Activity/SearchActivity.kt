package com.example.myapplication.Activity


import android.content.Context
import android.content.Intent
import android.graphics.Insets.add
import android.graphics.Rect
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.logging.Logger


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

                getSearch(binding.edtSearchHospital.text.toString())
                binding.edtSearchHospital.hideKeyboard()
            }
            true
        }

        binding.ivSearchLeftArrow.setOnClickListener {

            binding.edtSearchHospital.text = null
            binding.clRelativeKeywords.visibility = View.GONE
            if (adapter.isSearch) {
                animationCustom()
                floatingButton()
                adapter.isSearch = false
                adapter.searchHistoryList.clear()
                var currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
                if (currentSearchList == null) { currentSearchList = mutableListOf()}
                adapter.searchHistoryList.addAll(currentSearchList)
                adapter.notifyDataSetChanged()
            } else finish()
        }

        binding.searchResultFab.setOnClickListener {

            val searchModel = searchResultData[0]

            val intent = Intent(this,MapActivity::class.java )
            intent.putExtra("object",searchModel)
            startActivity(intent)
        }


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView = currentFocus
        if (focusView != null && ev != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()

            if (!rect.contains(x, y)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }




    private fun getSearch(searchKeyWord: String) {
        val api = SearchApi.create()
        api.getSearchApi(ApiUrlActivity.searchApiKey, "5", "1", searchKeyWord).enqueue(object :
            Callback<ResultCode> {
            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                val responseSearch = response.body()


                if (responseSearch != null) {
                    Log.d("eee", responseSearch.resultCode.toString())
                    if(responseSearch.resultCode == 3){
                        Log.d("eee", "??????")
                        Toast.makeText(this@SearchActivity, "????????? ????????????",Toast.LENGTH_SHORT).show()
                        return
                    }

                    Log.d("aaaa",responseSearch.toString() )
                    var currentSearchList = ApiUrlActivity.prefs.getSearchKeyWords(ApiUrlActivity.searchListPrefKey)
                    if (currentSearchList == null) {currentSearchList = mutableListOf()}

                    var now = LocalDate.now()
                    var setNow = now.format(DateTimeFormatter.ofPattern("MM.dd"))

                    var isExist = false
                    var checkIndex = -1

                    for(i in 0 until currentSearchList.size) {
                        if(searchKeyWord == currentSearchList[i].searchKeyWords) {
                            isExist = true
                            checkIndex = i
                        }
                    }
                    if(isExist) {
                        currentSearchList.removeAt(checkIndex)

                    }
                    var setList = mutableListOf<CurrentSearchModel>()
                    setList.add(CurrentSearchModel(searchKeyWord , setNow))
                    for(i in currentSearchList){
                        setList.add(i)
                    }
                    ApiUrlActivity.prefs.setSearchKeyWords(ApiUrlActivity.searchListPrefKey, setList)






                    floatingButton()
                    Log.d("sss", searchKeyWord)
                    animationCustom()
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
        if (adapter.isSearch)
            binding.searchResultFab.visibility = View.GONE
        else binding.searchResultFab.visibility = View.VISIBLE
    }


    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onBackPressed() {
        Log.d("ppp", "???????????? ")
        super.onBackPressed()
        finish()
    }
    private fun setRecommendList() {
        searchRecommendList.add(SearchRecommendModel("??????????????????"))
        searchRecommendList.add(SearchRecommendModel("??????"))
        searchRecommendList.add(SearchRecommendModel("??????????????????"))
        searchRecommendList.add(SearchRecommendModel("???????????????"))
        searchRecommendList.add(SearchRecommendModel("??????"))
        searchRecommendList.add(SearchRecommendModel("??????"))
        searchRecommendList.add(SearchRecommendModel("???????????????"))
        searchRecommendList.add(SearchRecommendModel("?????????????????????"))
        searchRecommendList.add(SearchRecommendModel("??????????????????"))
        searchRecommendList.add(SearchRecommendModel("PCR??????"))
        searchRecommendList.add(SearchRecommendModel("???????????????????????????"))


    }

    private fun animationCustom(){
        val constraintSet = ConstraintSet()   // ???
        val constraintLayout = binding.clHeader // ??????  ????????????????????? ??? ???????????????
        constraintSet.clone(constraintLayout) // ????????? ?????????????????? ??????(?????? ????????????)

        if(adapter.isSearch) binding.llHeader.removeView(relationCustomView)// isSearch ??? ??? ????????? ?????? ??????
        else binding.llHeader.addView(relationCustomView)

        constraintSet.applyTo(constraintLayout)  //??????

        val trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.clHeader, trans) // ??????????????? ??????
    }




}
