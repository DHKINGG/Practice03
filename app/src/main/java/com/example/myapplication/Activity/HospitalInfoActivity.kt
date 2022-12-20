package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHospitalInfoBinding
import com.example.myapplication.databinding.ActivityMainBinding

class HospitalInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHospitalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }
}