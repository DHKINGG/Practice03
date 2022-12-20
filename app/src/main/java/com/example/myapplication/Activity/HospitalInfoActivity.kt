package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.HospitalMultiAdapter
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.R
import com.example.myapplication.StickHeader.RecyclerSectionItemDecoration

import com.example.myapplication.databinding.ActivityHospitalInfoBinding

class HospitalInfoActivity : AppCompatActivity() {

    private val adapter: HospitalMultiAdapter = HospitalMultiAdapter()
    private lateinit var binding: ActivityHospitalInfoBinding
    private lateinit var hospitalData: SearchModel
    private var vData = mutableListOf<HospitalInfoViewPager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setTopInfoViewPager()
        hospitalData = intent.getSerializableExtra("object") as SearchModel


        binding.rvHospitalInfo.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        adapter.hospitalList = hospitalData
        adapter.topViewpager = vData
        binding.rvHospitalInfo.adapter = adapter

        val sectionItemDecoration = RecyclerSectionItemDecoration(getSectionCallback())
        binding.rvHospitalInfo.addItemDecoration(sectionItemDecoration)
    }


    private fun getSectionCallback(): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return adapter.isHolder(position)
            }


            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderLayoutView(list, position)
            }

        }
    }

    private fun setTopInfoViewPager() {
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
    }
}