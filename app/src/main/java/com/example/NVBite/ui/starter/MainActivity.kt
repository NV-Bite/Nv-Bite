package com.example.NVBite.ui.starter

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.NVBite.R
import com.example.NVBite.data.locals.AccountPreferences
import com.example.NVBite.databinding.ActivityMainBinding
import com.example.NVBite.ui.home.HomeActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModel.Build(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeToken()

        // Mengatur padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Memuat fragmen
        val starterAdapter = StarterPagerAdapter(this)
        binding.apply {
            viewPager.adapter = starterAdapter
            TabLayoutMediator(binding.dotIndicator, viewPager) { tab, position -> }.attach() //The Magical Line
        }
    }

    private fun observeToken() {
        mainViewModel.getToken().observe(this) { token ->
            if (token != AccountPreferences.preferencesDefaultValue) {
                val iMain = Intent(this, HomeActivity::class.java)
                finishAffinity()
                startActivity(iMain)
            }
        }
    }

    override fun onBackPressed() {
        // Periksa apakah ada fragmen dalam back stack
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed() // Keluar dari aktivitas
        }
    }
}