package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bakeboutique.databinding.MenuItemBinding
import com.model.MenuItem

class MenuAdapter(
    private val items: MutableList<MenuItem>,
    private val onAddToCartClickListener: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {
            binding.menufoodName.text = item.foodname
            binding.menuPrice.text = item.foodprice
            binding.menuImage.setImageResource(item.foodImageUrl)

            binding.menuAddToCart.setOnClickListener {
                onAddToCartClickListener.invoke(item)
                Toast.makeText(itemView.context, "${item.foodname} added to cart", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

