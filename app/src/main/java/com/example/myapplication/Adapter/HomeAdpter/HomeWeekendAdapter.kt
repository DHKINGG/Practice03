package com.example.myapplication.Adapter.HomeAdpter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.databinding.IvWeekendHotspitalBinding

class HomeWeekendAdapter : RecyclerView.Adapter<HomeWeekendAdapter.Holder>() {

    var list = mutableListOf<HomeBookModel>()
    lateinit var adapterContext: Context

    inner class Holder(private val binding: IvWeekendHotspitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeBookModel) {
            Log.d("hospitalname", item.hospitalName)
            binding.tvHospitalName.text = item.hospitalName
            binding.tvHomeHospitalType.text = item.hospitalType
//            binding.tvHomeBookHospitalDistance.text = item.hospitalDistance
//            binding.tvHomeHospitalAddress.text = item.HospitalLocation
//            binding.tvHomeBookHospitalMore.text = item.waiting
//            binding.tvHomeBookHospitalTime2.text = item.waiting2



        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            IvWeekendHotspitalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        Log.d("hospitalname", list.size.toString())
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}