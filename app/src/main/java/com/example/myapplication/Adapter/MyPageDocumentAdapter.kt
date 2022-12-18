package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.DocumentModel
import com.example.myapplication.databinding.IvBookHospitalBinding
import com.example.myapplication.databinding.IvMpDocumentBinding

class MyPageDocumentAdapter : RecyclerView.Adapter<MyPageDocumentAdapter.Holder>() {

    var list = mutableListOf<DocumentModel>()
    lateinit var adapterContext: Context


    inner class Holder(private val binding: IvMpDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DocumentModel) {
            binding.tvDocument.text = item.text1
            binding.viewBar.visibility = item.bar
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageDocumentAdapter.Holder {
        return Holder(
            IvMpDocumentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyPageDocumentAdapter.Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }


}