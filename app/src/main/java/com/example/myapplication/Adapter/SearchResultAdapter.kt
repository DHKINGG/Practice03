package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.HospitalInfoActivity
import com.example.myapplication.Model.HomeBookModel
import com.example.myapplication.Model.SearchModel
import com.example.myapplication.databinding.IvSearchResultBinding

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    lateinit var adapterContext: Context
    private var searchResultData = mutableListOf<SearchModel>()
    var list = mutableListOf<SearchModel>()



    inner class Holder(private val binding: IvSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchModel) {

            binding.tvBookHospitalName.text = item.hospitalName
            binding.tvSearchResultHospitalAddress.text = item.hospitalAddress

            binding.clHospitalInfo.setOnClickListener {


                val intent = Intent(adapterContext, HospitalInfoActivity::class.java)
                intent.putExtra("object", item)
                intent.run { adapterContext.startActivity(this) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultAdapter.Holder {
        return Holder(
            IvSearchResultBinding.inflate(
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
        Log.d("vvv", list.size.toString())
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}