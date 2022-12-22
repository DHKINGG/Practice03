package com.example.myapplication.Adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.R
import com.example.myapplication.databinding.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import timber.log.Timber


class HospitalMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    lateinit var hospitalList: SearchModel
    var topViewpager = mutableListOf<HospitalInfoViewPager>()
    lateinit var recyclerView: RecyclerView

    private lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView


    inner class TopInfoHeader(private val binding: IvHospitalInfoTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchModel) {
            var viewpagerAdapter = HospitalViewpagerAdapter()
            viewpagerAdapter.list = topViewpager
            viewpagerAdapter.setContext(adapterContext)
            binding.vpViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.vpViewPager2.adapter = viewpagerAdapter

            binding.tvHospitalName.text = item.hospitalName
            binding.tvLocation.text = item.hospitalAddress


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
        fun bind(item: SearchModel){
            binding.tvHospitalSubLocation.text = item.hospitalAddress
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
        fun bind(item: SearchModel) {
            mapView = binding.fragmentContainerMap
            mapView.getMapAsync { p0 ->
                Log.d("ssss", "onMapReady")
                naverMap = p0

                val uiSettings = naverMap.uiSettings
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.464368299999826,127.10017120000005 ))
                naverMap.moveCamera(cameraUpdate)



                uiSettings.isLocationButtonEnabled = false
                uiSettings.isZoomControlEnabled = false
                naverMap.locationTrackingMode = LocationTrackingMode.Follow





                binding.tvLocationInfo2.text = item.hospitalAddress


            }
        }
    }



    inner class HospitalDiagnosis(private val binding:IvHospitalDiagnosisBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

    inner class HospitalDiagnosisInfo(private val binding:IvHospitalDiagnosisInfoBinding):
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
            4 -> {
                return HospitalMap(
                    IvHospitalMapBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            5 -> {
                return HospitalDiagnosis(
                    IvHospitalDiagnosisBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return HospitalDiagnosisInfo(
                    IvHospitalDiagnosisInfoBinding.inflate(
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
            0 -> (holder as HospitalMultiAdapter.TopInfoHeader).bind(hospitalList)
            1 -> (holder as HospitalMultiAdapter.StickyHeader).bind()
            2 -> (holder as HospitalMultiAdapter.HospitalType).bind(hospitalList)
            3 -> (holder as HospitalMultiAdapter.HospitalTime).bind()
            4 -> (holder as HospitalMultiAdapter.HospitalMap).bind(hospitalList)
            5 -> (holder as HospitalMultiAdapter.HospitalDiagnosis).bind()
            else -> (holder as HospitalMultiAdapter.HospitalDiagnosisInfo).bind()
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