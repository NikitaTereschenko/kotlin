package com.example.shopapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopapp.Fragments.SuccessFragment
import com.example.shopapp.databinding.ActivityDetailsPaymentBinding

class DetailsPaymentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailsPaymentBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.payButton.setOnClickListener {
            val bottomFragment = SuccessFragment()
            bottomFragment.show(supportFragmentManager, "test")
            var db = DbHelper(this, null)
            db.addOrder(programData.userName, intent.getStringExtra("totalPrice").toString())
        }

        val totalPrice = intent.getStringExtra("totalPrice") + "Тг"
        binding.payAmount.text = totalPrice
    }
}