package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.SearchActivity
import com.example.myapplication.Model.HomeAd
import com.example.myapplication.Model.HomeBottomAd
import com.example.myapplication.Model.HomeReceipt
import com.example.myapplication.Model.HomeWeekend
import com.example.myapplication.databinding.*

class HomeMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    var list = mutableListOf<HomeAd>()


    var bookList = mutableListOf<HomeReceipt>()
    var weekendList = mutableListOf<HomeWeekend>()
    var homeBottomAdList = mutableListOf<HomeBottomAd>()
    var homeAdList = mutableListOf<HomeAd>()

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

        }
    }

    inner class HomeFavoriteHolder(private val binding: IvFavoriteMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    inner class HomeBookHolder(private val binding: IvBookHospitalMultiBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: MutableList<HomeReceipt>){
            binding.rvHomeBook.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = HomeBookAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvHomeBook.adapter = recyclerAdapter
        }
    }


    inner class HomeWeekendHolder(private val binding: IvWeekendHotspitalMultiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:MutableList<HomeWeekend>){
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
            3-> (holder as HomeBookHolder).bind(bookList)
            4-> (holder as HomeWeekendHolder).bind(weekendList)
            5-> (holder as HomeBottomAdHolder).bind()
            else -> (holder as HomePharmacyHolder).bind()
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 6
    }

    fun setContext(context: Context) {
        adapterContext = context
    }


}