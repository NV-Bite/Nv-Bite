package com.example.NVBite.ui.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.NVBite.R
import com.example.NVBite.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    navView.menu.getItem(0).isChecked = true
                }

                R.id.navigation_camera -> {
                    navController.navigate(R.id.navigation_camera)
                    navView.menu.getItem(1).isChecked = true
                }

                R.id.navigation_history -> {
                    navController.navigate(R.id.navigation_history)
                    navView.menu.getItem(2).isChecked = true
                }

                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    navView.menu.getItem(3).isChecked = true
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun clearAllActiveMenu() {
        val menu = navView.menu
        menu.setGroupCheckable(0, true, false)
        for (i in 0 until menu.size()) {
            menu.getItem(i).isChecked = false
        }
        menu.setGroupCheckable(0, true, true)
    }
}