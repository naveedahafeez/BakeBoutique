package com.example.bakeboutique

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakeboutique.databinding.ItemViewBinding

class ItemsAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private var quantity: Int = 1

        fun bind(item: Item) {
            binding.itemName.text = item.name
            binding.itemPrice.text = item.price.toString()
            binding.itemImage.setImageResource(item.imageResId)
            binding.tvQuantity.text = quantity.toString()

            binding.btnIncrease.setOnClickListener {
                quantity++
                binding.tvQuantity.text = quantity.toString()
            }

            binding.btnDecrease.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    binding.tvQuantity.text = quantity.toString()
                }
            }

            binding.btnDelete.setOnClickListener {
            }
        }
    }
}
