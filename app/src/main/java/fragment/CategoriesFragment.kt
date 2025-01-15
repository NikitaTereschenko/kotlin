package fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.Adapters.PopularItemsAdapter
import com.example.shopapp.DbHelper
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentCategories2Binding
import com.example.shopapp.programData
import kotlin.collections.ArrayList

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategories2Binding
    private lateinit var adapter : PopularItemsAdapter
    private lateinit var list : ArrayList<PopularModel>
    private lateinit var menuRv : RecyclerView
    private lateinit var search : SearchView
    private lateinit var sharedModel : SharedModel


    //private lateinit var view : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategories2Binding.inflate(inflater, container, false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)

        list = ArrayList()
        var list2 = programData.getAllItems()
        if (list2 != null) {
            Log.d("My1", "not null")
            Log.d("www", list2.toString())
            for (item in list2) {
                var image = item.image
                Log.d("image: ", image)
                var trueImage = when (image) {
                    "item1" -> R.drawable.item1
                    "item2" -> R.drawable.item2
                    "item3" -> R.drawable.item3
                    "item4" -> R.drawable.item4
                    "item5" -> R.drawable.item5
                    "item6" -> R.drawable.item6
                    "item7" -> R.drawable.item7
                    "item8" -> R.drawable.item8
                    "item9" -> R.drawable.item9
                    "item10" -> R.drawable.item10
                    "item11" -> R.drawable.item11
                    "item12" -> R.drawable.item12
                    "item13" -> R.drawable.item13
                    "item14" -> R.drawable.item14
                    "item15" -> R.drawable.item15
                    "item16" -> R.drawable.item16
                    "pants1" -> R.drawable.pants1
                    "pants2" -> R.drawable.pants2
                    "pants3" -> R.drawable.pants3
                    "pants4" -> R.drawable.pants4
                    "pants5" -> R.drawable.pants5
                    "pants6" -> R.drawable.pants6
                    "pants7" -> R.drawable.pants7
                    "pants8" -> R.drawable.pants8
                    else -> R.drawable.item1
                }

                list.add(PopularModel(trueImage, item.name, item.price, item.description, item.ordersCount))
            }
        }

        adapter = PopularItemsAdapter(requireContext(), list)
        adapter.setSharedModel(sharedModel)

        binding.categoriesRV2.layoutManager = LinearLayoutManager(requireContext())
        binding.categoriesRV2.adapter = adapter
        searchMenuFood()
        return binding.root
    }


    private fun searchMenuFood() {
        binding.searchMenu.setOnQueryTextListener(object : OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)



                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)



                return true
            }
        })
    }

    private fun filterList(input : String?) {
        val filteredList = if (input.isNullOrEmpty()) {
            list
        } else {
            list.filter{ item -> item.getName().contains(input, ignoreCase = true)}
        }

        adapter.updateList(filteredList as ArrayList<PopularModel>)
    }


}