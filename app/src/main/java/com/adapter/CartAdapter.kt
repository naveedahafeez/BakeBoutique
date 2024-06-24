package com.example.bakeboutique

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakeboutique.databinding.CartItemBinding

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChangeListener: (Int, Int) -> Unit,
    private val onDeleteClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItems.size

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = cartItems[position]

            binding.apply {
                foodNameTextView.text = item.itemName
                priceTextView.text = item.price
                imageView5.setImageResource(item.imageResId)
                quantityTextView.text = item.quantity.toString()

                minusButton.setOnClickListener {
                    if (item.quantity > 1) {
                        onQuantityChangeListener(position, item.quantity - 1)
                    }
                }

                plusButton.setOnClickListener {
                    onQuantityChangeListener(position, item.quantity + 1)
                }

                deleteButton.setOnClickListener {
                    onDeleteClickListener(position)
                }
            }
        }
    }}