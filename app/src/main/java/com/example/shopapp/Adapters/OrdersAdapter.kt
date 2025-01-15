package com.example.shopapp.Adapters
import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.DetailsActivity
import com.example.shopapp.Models.OrderModel
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.databinding.ActivityMainWindowBinding
import com.example.shopapp.databinding.FragmentHome2Binding
import com.example.shopapp.databinding.HomeItemBinding
import com.example.shopapp.databinding.OrderItemBinding
import fragment.HomeFragment

class OrdersAdapter(
    val context : Context,
    var list : ArrayList<OrderModel>
) : RecyclerView.Adapter<OrdersAdapter.PopularViewHolder>() {

    private lateinit var sharedModel : SharedModel

    fun setSharedModel(viewModel : SharedModel) {
        sharedModel = viewModel
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersAdapter.PopularViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersAdapter.PopularViewHolder, position: Int) {
        val listModel = list[position]

        holder.itemName.text = "Заказ от " + listModel.getDate()
        holder.price.text = listModel.getAmount()

        /*holder.delBut.setOnClickListener {
            val intent = Intent(context, DetailsActivity:: class.java)
            intent.putExtra("itemImage", listModel.getImage())
            intent.putExtra("itemName", listModel.getName())
            context.startActivity(intent)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList : ArrayList<OrderModel>) {
        list = newList
        notifyDataSetChanged()
    }

    class PopularViewHolder(binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.itemName
        val price = binding.itemPrice
        //val delBut = binding.deleteButton

    }


}