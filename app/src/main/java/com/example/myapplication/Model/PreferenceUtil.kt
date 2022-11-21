package com.example.myapplication.Model

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.Activity.ApiUrlActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)


    fun getSearchKeyWords(key: String): MutableList<CurrentSearchModel> {
        val json = prefs.getString(key, "").toString() // sp에서 꺼내오는
        val gson = Gson()
        val token: TypeToken<MutableList<CurrentSearchModel>> = object : TypeToken<MutableList<CurrentSearchModel>>() {} // 어떤타입의 토큰을 가져올건지
        val currentSearchList: MutableList<CurrentSearchModel>? = gson.fromJson(json, token.type)   // gson을 사용해서 json을 object로 바꿈


        return currentSearchList ?: mutableListOf()
    }

    fun setSearchKeyWords(key: String, listObject: MutableList<CurrentSearchModel>) {
        val gson = Gson()
        val json: String = gson.toJson(listObject)
        prefs.edit().putString(key,json).apply()
    }

    fun clearSearchKeyWords() {
        prefs.edit().remove(ApiUrlActivity.searchListPrefKey).commit()
    }
}

