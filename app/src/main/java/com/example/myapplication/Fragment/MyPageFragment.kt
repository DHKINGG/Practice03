package com.example.myapplication.Fragment

import android.content.Context
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.MyPageMultiAdapter
import com.example.myapplication.Model.DocumentModel
import com.example.myapplication.Model.ManagementModel
import com.example.myapplication.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    private var adapter = MyPageMultiAdapter()
    private var documentData = mutableListOf<DocumentModel>()
    private var managementData = mutableListOf<ManagementModel>()
    lateinit var adapterContext: Context

    override fun initView() {
        binding.rvMyPage.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMyPage.adapter = adapter
        adapter.documentList = documentData
        adapter.managementList = managementData
        adapter.setContext(requireContext())

        setDocumentData()
        setManagementData()


        val onScrollListener = object:RecyclerView.OnScrollListener() {
            var temp: Int = 0
            override fun onScrolled(@NonNull recyclerView:RecyclerView, dx:Int, dy:Int) {
                if(temp == 1) {
                    super.onScrolled(recyclerView, dx, 50)
                    binding.tvMyPage.visibility = View.VISIBLE
                    binding.tvMyPage.visibility = View.VISIBLE
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.tvMyPage.visibility = View.VISIBLE
                binding.tvMyPage.visibility = View.VISIBLE
                temp = 1
            }
        }



        binding.rvMyPage.setOnScrollListener(onScrollListener)


    }


    private fun setDocumentData() {
        documentData.add((DocumentModel(text1 = "실손보험 청구", bar = View.VISIBLE)))
        documentData.add((DocumentModel(text1 = "모바일 서류 보관함", bar = View.GONE)))

    }


    private fun setManagementData() {
        managementData.add(ManagementModel(text = "결제수단 관리", bar = View.VISIBLE))
        managementData.add(ManagementModel(text = "복약관리", bar = View.GONE))
    }


    fun setContext(context: Context) {
        adapterContext = context
    }


}


