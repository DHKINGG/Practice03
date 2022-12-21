package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        adapter.recyclerView = binding.rvHospitalInfo
        binding.rvHospitalInfo.adapter = adapter

        val sectionItemDecoration = RecyclerSectionItemDecoration(getSectionCallback())
        binding.rvHospitalInfo.addItemDecoration(sectionItemDecoration)

        binding.leftArrow.setOnClickListener {
            binding.rvHospitalInfo.smoothScrollToPosition(3)
        }

        binding.headerLl1.setOnClickListener {
            binding.rvHospitalInfo.smoothScrollToPosition(1)
            Log.d("sss", "click1")
        }
        binding.headerLl2.setOnClickListener {
            binding.rvHospitalInfo.smoothScrollToPosition(3)
            Log.d("sss", "click2")
        }
        binding.headerLl3.setOnClickListener {
            binding.rvHospitalInfo.smoothScrollToPosition(4)
            Log.d("sss", "click3")
        }
    }


    private fun getSectionCallback(): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return false
            }
            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                val lastIndex =
                    if (position < adapter.itemCount) position else adapter.itemCount - 1

                if (1 in 0..lastIndex) {
                    binding.llHospitalHeader.visibility = View.VISIBLE
                    binding.llHospitalHeader.isClickable = true
                } else {
                    binding.llHospitalHeader.visibility = View.GONE
                }

                when (lastIndex) {
                    0 -> {
                        binding.headerBar1.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar2.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar3.setBackgroundColor(getColor(R.color.white))
                    }
                    in 1..2 -> {
                        binding.headerBar1.setBackgroundColor(getColor(R.color.black))
                        binding.headerBar2.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar3.setBackgroundColor(getColor(R.color.white))
                    }
                    3 -> {
                        binding.headerBar1.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar2.setBackgroundColor(getColor(R.color.black))
                        binding.headerBar3.setBackgroundColor(getColor(R.color.white))
                    }
                    4 -> {
                        binding.headerBar1.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar2.setBackgroundColor(getColor(R.color.white))
                        binding.headerBar3.setBackgroundColor(getColor(R.color.black))
                    }
                }

                return null
            }

        }
    }

    private fun setTopInfoViewPager() {
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
        vData.add(HospitalInfoViewPager(R.drawable.home_ad_1))
    }
}