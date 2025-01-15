package fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.Adapters.OrdersAdapter
import com.example.shopapp.Adapters.PopularItemsAdapter
import com.example.shopapp.Models.OrderModel
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.R
import com.example.shopapp.programData
import java.util.ArrayList

class UserFragment : Fragment() {

    private lateinit var list : ArrayList<OrderModel>
    private lateinit var adapter : OrdersAdapter
    private lateinit var sharedModel : SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)

        var userName = view.findViewById<TextView>(R.id.userName)
        userName.text = programData.userName

        list = ArrayList()
        var list2 = programData.getAllOrders()
        Log.d("test", "list2.count" + (list2?.count() ?: 0))
        if (list2 != null) {
            for (item in list2) {
                list.add(OrderModel(item.login, item.amount, item.date))
            }


            adapter = OrdersAdapter(requireContext(), list)
            adapter.setSharedModel(sharedModel)

            var orders = view.findViewById<RecyclerView>(R.id.ordersList)

            orders.layoutManager = LinearLayoutManager(requireContext())
            orders.adapter = adapter

        }


        return view
    }
}