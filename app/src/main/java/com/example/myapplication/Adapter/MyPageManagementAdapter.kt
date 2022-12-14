package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.ManagementModel
import com.example.myapplication.databinding.IvBookHospitalBinding
import com.example.myapplication.databinding.IvMpMyManagementBinding

class MyPageManagementAdapter: RecyclerView.Adapter<MyPageManagementAdapter.Holder>() {

   var list = mutableListOf<ManagementModel>()
   lateinit var adapterContext : Context

   inner class Holder(private val binding: IvMpMyManagementBinding) :
   RecyclerView.ViewHolder(binding.root){

       fun bind(item:ManagementModel){
           binding.tvManagement.text = item.text
       }

   }












    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageManagementAdapter.Holder {
        return Holder(
            IvMpMyManagementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyPageManagementAdapter.Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setContext(context: Context) {
        adapterContext = context
    }

}