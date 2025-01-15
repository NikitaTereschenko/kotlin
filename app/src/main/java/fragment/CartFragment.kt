package fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.Adapters.CartAdapter
import com.example.shopapp.DetailsPaymentActivity
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding

    //private lateinit var list : ArrayList<PopularModel>

    private lateinit var adapter : CartAdapter

    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)


        adapter = CartAdapter(requireContext(), sharedModel.cartItem.value ?: ArrayList())
        binding.itemsInCart.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsInCart.adapter = adapter

        binding.payButton.setOnClickListener {
            val totalPrice = sharedModel.cartItem.value?.let {it1 -> calculatePrice(it1)}
            val stringPrice = totalPrice.toString()
            val intent = Intent(requireContext(), DetailsPaymentActivity :: class.java)
            intent.putExtra("totalPrice", stringPrice)
            startActivity(intent)
        }

        return binding.root
    }


    fun calculatePrice(itemPrices : List<PopularModel>) : Int {
        var totalPrice = 0

        itemPrices.forEach {item ->
            val price = item.getPrice() * item.getCount()
            totalPrice += price
        }


        return totalPrice
    }

}