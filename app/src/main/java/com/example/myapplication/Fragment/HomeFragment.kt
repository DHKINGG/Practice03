package com.example.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.HomeMultiAdapter
import com.example.myapplication.Model.HomeAd
import com.example.myapplication.Model.HomeBottomAd
import com.example.myapplication.Model.HomeReceipt
import com.example.myapplication.Model.HomeWeekend
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private var homeAdData = mutableListOf<HomeAd>()
    private var homeBottomAdData = mutableListOf<HomeBottomAd>()
    private var bookData = mutableListOf<HomeReceipt>()
    private var weekendData = mutableListOf<HomeWeekend>()



    override fun initView() {

        setBookData()
        setHomeADData()
        setWeekendData()
        setBottomADData()

        binding.rvHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val adapter = HomeMultiAdapter()
        adapter.bookList = bookData
        adapter.homeAdList = homeAdData
        adapter.homeBottomAdList = homeBottomAdData
        adapter.weekendList = weekendData
        adapter.setContext(requireContext())
        binding.rvHome.adapter = adapter


    }


    private fun setHomeADData() {
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))
        homeAdData.add(HomeAd((R.drawable.home_ad_1)))

    }


    private fun setBookData() {
        bookData.add(HomeReceipt("더한별이비인후과의원", "이비인후과", "3km", "서울특별시 송파구 석촌동", "대기", "4명"))
        bookData.add(HomeReceipt("킴스의원", "내과", "4km", "서울특별시 송파구 거여동", "바로", "진료"))
        bookData.add(HomeReceipt("열린가정의학과의원", "가정의학과", "5km", "서울특별시 송파구 방이동", "바로", "진료"))
        bookData.add(HomeReceipt("공앤송이비인후과의원", "이비인후과", "3km", "서울특별시 송파구 석촌동", "대기", "4명"))
        bookData.add(HomeReceipt("미앤미의원", "이비인후과", "3km", "서울특별시 송파구 석촌동", "대기", "4명"))
        bookData.add(HomeReceipt("서초맑은이비인후과의원", "이비인후과", "3km", "서울특별시 송파구 석촌동", "대기", "4명"))
        bookData.add(HomeReceipt("서울유의원", "이비인후과", "3km", "서울특별시 송파구 석촌동", "대기", "4명"))

    }

    private fun setWeekendData() {
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
        weekendData.add(
            HomeWeekend(
                "송파성모정신겅강의학과의원",
                "정신건강의학과",
                "2km",
                "서울특별시 송파구 문정동",
                "30분 내 ",
                "진료"
            )
        )
    }

    private fun setBottomADData(){
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_1))
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_2))
        homeBottomAdData.add(HomeBottomAd(R.drawable.home_ad_3))
    }

}