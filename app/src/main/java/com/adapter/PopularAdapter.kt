package com.example.bakeboutique

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakeboutique.databinding.PopularItemBinding

class PopularAdapter(
    val items: List<String>,
    val images: List<Int>,
    val prices: List<String>,
    private val onAddClick: (Int) -> Unit
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val image = images[position]
        val price = prices[position]
        holder.bind(item, price, image)

        holder.itemView.setOnClickListener {
            // Handle item click if needed
        }

        holder.binding.addButton.setOnClickListener {
            onAddClick(position)
        }
    }

    override fun getItemCount(): Int = items.size

    class PopularViewHolder(val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, price: String, image: Int) {
            binding.apply {
                foodNameTextView.text = item
                priceTextView.text = price
                imageView5.setImageResource(image)
            }
        }
    }
}
