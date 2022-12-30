package com.example.myapplication.Adapter.HospitalInfoAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Model.HospitalInfoViewPager
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.R
import com.example.myapplication.databinding.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage


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
                recyclerView.smoothScrollToPosition(5)
            }
            binding.headerLl3.setOnClickListener {
                recyclerView.smoothScrollToPosition(6)
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
        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: SearchModel) {
            mapView = binding.vMapView
            mapView.getMapAsync { p0 ->
                Log.d("ssss", "onMapReady")
                naverMap = p0



                val uiSettings = naverMap.uiSettings
                val latLng = LatLng(36.334207, 127.372298)
//                val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.464368299999826,127.10017120000005 ))
                val cameraPosition = CameraPosition(latLng, 16.0)
                naverMap.cameraPosition = cameraPosition
//                naverMap.moveCamera(cameraUpdate)

                uiSettings.isLocationButtonEnabled = false
                uiSettings.isZoomControlEnabled = false
                naverMap.locationTrackingMode = LocationTrackingMode.Follow

                binding.tvLocationInfo2.text = item.hospitalAddress

                val marker = Marker()
                marker.position = latLng
                marker.map = naverMap
                marker.icon = OverlayImage.fromResource(R.drawable.marker_resize)
                marker.width = Marker.SIZE_AUTO
                marker.height = Marker.SIZE_AUTO

                mapView.setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_MOVE -> recyclerView.requestDisallowInterceptTouchEvent(
                            true
                        )
                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> recyclerView.requestDisallowInterceptTouchEvent(
                            false
                        )
                    }
                    mapView.onTouchEvent(event)
                }
                binding.clMapView.clipToOutline = true


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
            0 -> (holder as TopInfoHeader).bind(hospitalList)
            1 -> (holder as StickyHeader).bind()
            2 -> (holder as HospitalType).bind(hospitalList)
            3 -> (holder as HospitalTime).bind()
            4 -> (holder as HospitalMap).bind(hospitalList)
            5 -> (holder as HospitalDiagnosis).bind()
            else -> (holder as HospitalDiagnosisInfo).bind()
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