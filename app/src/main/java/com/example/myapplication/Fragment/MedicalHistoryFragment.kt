package com.example.myapplication.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Activity.MainActivity
import com.example.myapplication.Activity.SearchActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentMedicalHistoryBinding

class MedicalHistoryFragment :
    BaseFragment<FragmentMedicalHistoryBinding>(FragmentMedicalHistoryBinding::inflate) {


    override fun initView() {
        binding.ivGoMainActivity.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            this.activity?.startActivity(intent)
        }

    }
}