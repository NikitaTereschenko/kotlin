package com.example.shopapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.databinding.CartAddItemBinding
import com.example.shopapp.databinding.HomeItemBinding

class CartAdapter(
    val context : Context,
    var list : ArrayList<PopularModel>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding = CartAddItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val listModel = list[position]

        holder.itemName.text = listModel.getName()
        holder.price.text = listModel.getPrice().toString()
        listModel.getImage()?.let { holder.itemImage.setImageResource(it) }

        holder.plus.setOnClickListener {
            val count = listModel.getCount() + 1
            listModel.setCount(count)
            holder.count.text = listModel.getCount().toString()
        }

        holder.minus.setOnClickListener {
            if (listModel.getCount() > 1) {
                val count = listModel.getCount() -1
                listModel.setCount(count)
                holder.count.text = listModel.getCount().toString()
            }
//            else {
//                holder.bindItem()
//            }


        }
        holder.delete.setOnClickListener {
            holder.bindItem()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class CartViewHolder(binding: CartAddItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.itemName
        val price = binding.itemPrice
        val itemImage = binding.itemImage
        val count = binding.itemCount

        val plus = binding.plusButton
        val minus = binding.minusButton
        val delete = binding.deleteButton

        fun bindItem() {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                deleteItem(adapterPosition)
            }
        }
    }

    fun deleteItem(position: Int) {
        if (position in 0..list.size) {
            list.removeAt(position)
            notifyDataSetChanged()
            notifyItemRangeChanged(position, list.size)
        }
    }
}