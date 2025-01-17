package com.example.shopapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shopapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val itemImage = intent.getIntExtra("itemImage", 0)
        val itemName = intent.getStringExtra("itemName")

        binding.itemImage.setImageResource(itemImage)
        binding.itemName.text = itemName

        binding.backhome.setOnClickListener {
            finish()
        }
    }
}