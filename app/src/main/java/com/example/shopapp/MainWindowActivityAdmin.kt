package com.example.shopapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopapp.databinding.ActivityMainWindowAdminBinding
import com.example.shopapp.databinding.ActivityMainWindowBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragment.CategoriesFragment
import fragment.HomeFragment
import fragment.UserFragment

class MainWindowActivityAdmin : AppCompatActivity() {
    private lateinit var binding : ActivityMainWindowAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWindowAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationView = findNavController(R.id.frame_layout)
        bottomNavView.setupWithNavController(navigationView)

    }

}