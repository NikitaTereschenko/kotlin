package fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.shopapp.Adapters.ImageSliderAdapter
import com.example.shopapp.Adapters.PopularItemsAdapter
import com.example.shopapp.Fragments.AllItemsFragment
import com.example.shopapp.Fragments.MenuBottomSheerFragment
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.R
import com.example.shopapp.programData
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter : ImageSliderAdapter
    private lateinit var imageList : ArrayList<Int>
    private lateinit var handler : Handler

    private lateinit var popularAdapter : PopularItemsAdapter
    private lateinit var list: ArrayList<PopularModel>
    private lateinit var homeRv : RecyclerView

    private lateinit var goMenuText : Button
    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        //val view2 = inflater.inflate(R.layout.activity_main_window, container, false)
        viewPager2 = view.findViewById(R.id.imageSlider)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)

        homeRv = view.findViewById(R.id.home_RV)
        goMenuText = view.findViewById(R.id.moreButton)

        goMenuText.setOnClickListener {
            val bottomSheetMenu = AllItemsFragment()
            bottomSheetMenu.show(parentFragmentManager, "Test")
            //val bottomNavigationView = view2.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            //bottomNavigationView.setSelectedItemId(R.id.categories);

        }
        list = ArrayList()

        var list2 = programData.getTenItems()
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

        popularAdapter = PopularItemsAdapter(requireContext(), list)
        popularAdapter.setSharedModel(sharedModel)
        homeRv.layoutManager = LinearLayoutManager(requireContext())
        homeRv.adapter = popularAdapter



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 2000)
    }

    private fun init() {
        imageList = ArrayList()
        adapter = ImageSliderAdapter(requireContext(), imageList, viewPager2)
        handler = Handler(Looper.myLooper()!!)

        imageList.add(R.drawable.goodbanner1)
        imageList.add(R.drawable.goodbanner6)
        imageList.add(R.drawable.goodbanner2)
        imageList.add(R.drawable.goodbanner5)
        imageList.add(R.drawable.goodbanner4)
        imageList.add(R.drawable.goodbanner3)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

    private fun setTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer{page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.14f

        }
        viewPager2.setPageTransformer(transformer)
    }
}