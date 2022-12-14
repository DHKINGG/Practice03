package com.example.myapplication.Adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.DocumentModel
import com.example.myapplication.Model.ManagementModel
import com.example.myapplication.databinding.*


class MyPageMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var adapterContext: Context
    var documentList = mutableListOf<DocumentModel>()
    var managementList = mutableListOf<ManagementModel>()

    inner class TopInfoHolder(private val binding: IvMpTopIconMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.main.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                //스크롤 아래로
                if (scrollY > oldScrollY) {
                    val anim = TranslateAnimation(0f, binding.tvMyPage.width.toFloat(), 0f, 0f)
                    anim.duration = 100
                    binding.tvMyPage.animation = anim
                    binding.tvMyPage.visibility = View.GONE
                    binding.btnModift.visibility = View.GONE

                }
                if (scrollY + 5 < oldScrollY) {
                    val anim = TranslateAnimation(binding.tvMyPage.width.toFloat() + 1000, 0f, 0f, 0f)
                    anim.duration = 100
                    binding.tvMyPage.animation = anim
                    binding.tvMyPage.visibility = View.GONE
                    binding.btnModift.visibility = View.GONE
                }
            }

        }
    }

    inner class DocumentHolder(private val binding: IvMpDocumentMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<DocumentModel>) {
            binding.rvDocument.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = MyPageDocumentAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvDocument.adapter = recyclerAdapter
        }
    }


    inner class ManagementHolder(private val binding: IvMpMyManagementMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<ManagementModel>) {
            binding.rvManagement.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = MyPageManagementAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvManagement.adapter = recyclerAdapter
        }

    }

    inner class ServiceHolder(private val binding: IvMpServiceCenterMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return TopInfoHolder(
                    IvMpTopIconMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            1 -> {
                return DocumentHolder(
                    IvMpDocumentMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            2 -> {
                return ManagementHolder(
                    IvMpMyManagementMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return ServiceHolder(
                    IvMpServiceCenterMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as MyPageMultiAdapter.TopInfoHolder).bind()
            1 -> (holder as MyPageMultiAdapter.DocumentHolder).bind(documentList)
            2 -> (holder as MyPageMultiAdapter.ManagementHolder).bind(managementList)
            else -> (holder as MyPageMultiAdapter.ServiceHolder).bind()
        }

    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}


