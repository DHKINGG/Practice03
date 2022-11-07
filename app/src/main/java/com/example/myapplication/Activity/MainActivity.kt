package com.example.myapplication.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragment.HomeFragment
import com.example.myapplication.Fragment.MedicalHistoryFragment
import com.example.myapplication.Fragment.MyPageFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        // transaction : 작업의 단위
        supportFragmentManager.beginTransaction().add(binding.flMainHome.id, HomeFragment())
            .commit()
        binding.btnNav.setOnNavigationItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.menu_home -> HomeFragment()
                    R.id.menu_medical_history -> MedicalHistoryFragment()
                    else -> MyPageFragment()

                }
            )
            true
        }
    }

     fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.flMainHome.id, fragment).commit()
    }


}