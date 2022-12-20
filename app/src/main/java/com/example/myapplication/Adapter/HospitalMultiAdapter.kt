package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.R
import com.example.myapplication.databinding.*


class HospitalMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var adapterContext: Context
    lateinit var hospitalList: SearchModel
    var topViewpager = mutableListOf<HospitalInfoViewPager>()

    inner class TopInfoHeader(private val binding: IvHospitalInfoTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            var viewpagerAdapter = HospitalViewpagerAdapter()
            viewpagerAdapter.list = topViewpager
            viewpagerAdapter.setContext(adapterContext)
            binding.vpViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager2.adapter = viewpagerAdapter
        }
    }

    inner class StickyHeader(private val binding: HospitalInfoHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return TopInfoHeader(
                    IvHospitalInfoTopBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            1 -> {
                return StickyHeader(
                    HospitalInfoHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            2 -> {
                return TopInfoHeader(
                    IvHospitalInfoTopBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            3 -> {
                return TopInfoHeader(
                    IvHospitalInfoTopBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return TopInfoHeader(
                    IvHospitalInfoTopBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as HospitalMultiAdapter.TopInfoHeader).bind()
            1 -> (holder as HospitalMultiAdapter.StickyHeader).bind()
            2 -> (holder as HospitalMultiAdapter.TopInfoHeader).bind()
            3 -> (holder as HospitalMultiAdapter.TopInfoHeader).bind()
            else -> (holder as HospitalMultiAdapter.TopInfoHeader).bind()
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

    // 붙일 헤더뷰인지 아닌지
    fun isHolder(position: Int): Boolean {
        return position == 1
    }

    // 헤더 뷰보다 스크롤이 내려와있으면 헤더뷰를 리턴 아니면 리턴 null
    fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
        val lastIndex =
            if (position < itemCount) position else itemCount - 1
        for (index in lastIndex downTo 0) {
            if (index == 1) {
                return LayoutInflater.from(list.context)
                    .inflate(R.layout.hospital_info_header, list, false);
            }
        }
        return null
    }
}