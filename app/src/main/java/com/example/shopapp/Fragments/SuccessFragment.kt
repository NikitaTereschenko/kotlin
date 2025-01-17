package com.example.shopapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.shopapp.MainWindowActivity
import com.example.shopapp.MainWindowActivityAdmin
import com.example.shopapp.MainWindowActivityNotAdmin
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityDetailsBinding
import com.example.shopapp.programData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SuccessFragment : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_success, container, false)

        val goHome = view.findViewById<Button>(R.id.backHomeButton)
        goHome.setOnClickListener {
            if (programData.adminFlag) {
                val intent = Intent(requireContext(), MainWindowActivityAdmin::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(requireContext(), MainWindowActivity::class.java)
                startActivity(intent)
            }

        }
        return view
    }

}