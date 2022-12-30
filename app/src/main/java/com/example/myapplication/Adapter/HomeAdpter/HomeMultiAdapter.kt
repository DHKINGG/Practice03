

package com.example.myapplication.Adapter.HomeAdpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Activity.SearchActivity
import com.example.myapplication.Model.*
import com.example.myapplication.databinding.*
import com.google.android.material.tabs.TabLayoutMediator


class HomeMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context


    var bookList = mutableListOf<HomeReceipt>()
    var weekendList = mutableListOf<HomeBookModel>()
    var homeBottomAdList = mutableListOf<HomeBottomAd>()
    var homeAdList = mutableListOf<HomeAd>()
    var homeBookList = mutableListOf<HomeBookModel>()

    inner class HeaderSearchHolder(private val binding: IvHomeHeaderMulitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.clHomeSearch.setOnClickListener {
                val intent = Intent(adapterContext, SearchActivity::class.java)
                adapterContext.startActivity(intent)
            }

        }
    }

    inner class HomeAdHolder(private val binding: IvViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            var viewPagerAdapter = HomeAdViewPagerAdapter()
            viewPagerAdapter.list = homeAdList
            viewPagerAdapter.setContext(adapterContext)
            binding.vpViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager.adapter = viewPagerAdapter


            TabLayoutMediator(binding.tlIndicator, binding.vpViewPager) { tab, position -> }.attach()


        }
    }

    inner class HomeFavoriteHolder(private val binding: IvFavoriteMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {


        }
    }

    inner class HomeBookHolder(private val binding: IvBookHospitalMultiBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: MutableList<HomeBookModel>){
            binding.rvHomeBook.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = HomeBookAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvHomeBook.adapter = recyclerAdapter
        }
    }


    inner class HomeWeekendHolder(private val binding: IvWeekendHotspitalMultiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:MutableList<HomeBookModel>){
            binding.rvHomeWeekend.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = HomeWeekendAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvHomeWeekend.adapter = recyclerAdapter


        }
    }

    inner class HomeBottomAdHolder(private val binding: IvViewPager2Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
            var viewPagerAdapter = HomeBottomViewPagerAdAdapter()
            viewPagerAdapter.list = homeBottomAdList
            binding.vpViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager2.adapter = viewPagerAdapter

        }
    }

    inner class HomePharmacyHolder(private val binding: IvAroundPharmacyMultiBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return HeaderSearchHolder(
                    IvHomeHeaderMulitBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            1 -> {
                return HomeAdHolder(
                    IvViewPagerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            2 -> {
                return HomeFavoriteHolder(
                    IvFavoriteMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            3 -> {
                return HomeBookHolder (
                    IvBookHospitalMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            4 -> {
                return HomeWeekendHolder (
                    IvWeekendHotspitalMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            5 -> {
                return HomeBottomAdHolder (
                    IvViewPager2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }


            else -> {
                return HomePharmacyHolder(
                    IvAroundPharmacyMultiBinding.inflate(
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
            0 -> (holder as HeaderSearchHolder).bind()
            1-> (holder as HomeAdHolder).bind()
            2-> (holder as HomeFavoriteHolder).bind()
            3-> (holder as HomeBookHolder).bind(homeBookList)
            4-> (holder as HomeWeekendHolder).bind(weekendList)
            5-> (holder as HomeBottomAdHolder).bind()
            else -> (holder as HomePharmacyHolder).bind()
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 7
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}