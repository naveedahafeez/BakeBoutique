package com.example.bakeboutique

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakeboutique.databinding.ItemRecentlyBoughtProductBinding

class RecentlyBoughtProductAdapter(
    private val products: List<Product>,
    private val context: Context
) : RecyclerView.Adapter<RecentlyBoughtProductAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecentlyBoughtProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productImageView.setImageResource(product.imageResId)
            binding.productNameTextView.text = product.name
            binding.productPriceTextView.text = product.price

            binding.buyAgainButton.setOnClickListener {

                val intent = Intent(context, CheckoutActivity::class.java).apply {
                    putExtra("TOTAL_BILL", product.price)
                    putParcelableArrayListExtra("CART_ITEMS", arrayListOf(CartItem(product.name, product.price, product.imageResId, 1)))
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecentlyBoughtProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
