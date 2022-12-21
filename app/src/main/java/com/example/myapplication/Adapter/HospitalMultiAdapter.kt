package com.example.myapplication.Adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.R
import com.example.myapplication.databinding.*
import com.gun0912.tedpermission.provider.TedPermissionProvider.context


class HospitalMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    lateinit var hospitalList: SearchModel
    var topViewpager = mutableListOf<HospitalInfoViewPager>()
    lateinit var recyclerView: RecyclerView

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
            binding.headerLl1.setOnClickListener {
                recyclerView.smoothScrollToPosition(1)
            }
            binding.headerLl2.setOnClickListener {
                recyclerView.smoothScrollToPosition(3)
            }
            binding.headerLl3.setOnClickListener {
                recyclerView.smoothScrollToPosition(4)
            }
        }
    }

    inner class HospitalType(private val binding:IvHospitalLocationBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }


    inner class HospitalTime(private val binding:IvHospitalTimeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.clDayTimeTable.clipToOutline = true
        }
    }

    inner class HospitalMap(private val binding:IvHospitalMapBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(){

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
                return HospitalType(
                    IvHospitalLocationBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            3 -> {
                return HospitalTime(
                    IvHospitalTimeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return HospitalMap(
                    IvHospitalMapBinding.inflate(
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
            2 -> (holder as HospitalMultiAdapter.HospitalType).bind()
            3 -> (holder as HospitalMultiAdapter.HospitalTime).bind()
            else -> (holder as HospitalMultiAdapter.HospitalMap).bind()
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}