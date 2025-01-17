package com.example.shopapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.Adapters.PopularItemsAdapter
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentCategories2Binding
import com.example.shopapp.databinding.FragmentMenuBottomSheerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheerFragment : BottomSheetDialogFragment() {

    private lateinit var adapter : PopularItemsAdapter
    private lateinit var menuList : ArrayList<PopularModel>
    private lateinit var menuRv : RecyclerView

    private lateinit var binding: FragmentMenuBottomSheerBinding
    private lateinit var sharedModel : SharedModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_bottom_sheer, container, false)
        /*menuList = ArrayList()

        menuList.add(PopularModel(R.drawable.banner_1, "TEST1", "500 ТГ"))
        menuList.add(PopularModel(R.drawable.banner_2, "TEST2", "1500 ТГ"))
        menuList.add(PopularModel(R.drawable.banner_3, "TEST3", "3500 ТГ"))


        adapter = PopularItemsAdapter(requireContext(), menuList)

        menuRv = view.findViewById(R.id.categories_RV)

        menuRv.layoutManager = LinearLayoutManager(requireContext())
        menuRv.adapter = adapter*/
        return view
    }

}