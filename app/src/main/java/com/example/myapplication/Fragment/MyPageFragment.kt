package com.example.myapplication.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.MyPageMultiAdapter
import com.example.myapplication.Model.DocumentModel
import com.example.myapplication.Model.ManagementModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate){

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

    }




    private fun setDocumentData(){
        documentData.add((DocumentModel(text1 = "실손보험 청구")))
        documentData.add((DocumentModel(text1 = "모바일 서류 보관함")))
    }


    private fun setManagementData(){
        managementData.add(ManagementModel(text = "결제수단 관리"))
        managementData.add(ManagementModel(text = "복약관리"))
    }


    fun setContext(context: Context) {
        adapterContext = context
    }


}