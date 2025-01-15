package com.example.shopapp.Adapters
import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.DetailsActivity
import com.example.shopapp.Models.PopularModel
import com.example.shopapp.Models.SharedModel
import com.example.shopapp.databinding.ActivityMainWindowBinding
import com.example.shopapp.databinding.FragmentHome2Binding
import com.example.shopapp.databinding.HomeItemAdminBinding
import com.example.shopapp.databinding.HomeItemBinding
import com.example.shopapp.programData
import fragment.HomeFragment

class PopularItemsAdapterAdmin(
    val context : Context,
    var list : ArrayList<PopularModel>
) : RecyclerView.Adapter<PopularItemsAdapterAdmin.PopularViewHolder>() {

    private lateinit var sharedModel : SharedModel

    fun setSharedModel(viewModel : SharedModel) {
        sharedModel = viewModel
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularItemsAdapterAdmin.PopularViewHolder {
        val binding = HomeItemAdminBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularItemsAdapterAdmin.PopularViewHolder, position: Int) {
        val listModel = list[position]

        holder.itemName.text = listModel.getName()
        holder.price.text = listModel.getPrice().toString()
        listModel.getImage()?.let { holder.itemImage.setImageResource(it) }


        holder.item.setOnClickListener {
            val intent = Intent(context, DetailsActivity:: class.java)
            intent.putExtra("itemImage", listModel.getImage())
            intent.putExtra("itemName", listModel.getName())
            context.startActivity(intent)
        }

        holder.addBtn.setOnClickListener {
            if (sharedModel.inList(listModel)) {
                sharedModel.deleteFromCart(listModel)
                holder.addBtn.text = "В корзину"
            }
            else {
                sharedModel.addToCart(listModel)
                holder.addBtn.setText("Удалить")
            }
        }

        holder.delBtn.setOnClickListener {
            programData.deleteObject(listModel.getName())
            holder.bindItem()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList : ArrayList<PopularModel>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class PopularViewHolder(binding: HomeItemAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.itemName
        val price = binding.itemPrice
        val itemImage = binding.itemImage


        val addBtn = binding.addToCartBtn
        val delBtn = binding.deleteButton
        val item = binding.root

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