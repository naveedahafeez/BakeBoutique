package com.example.bakeboutique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakeboutique.databinding.FragmentSerachBinding
import com.model.MenuItem
import com.adapter.MenuAdapter

class SerachFragment : Fragment() {
    private lateinit var binding: FragmentSerachBinding
    private lateinit var adapter: MenuAdapter

    private val originalMenuFoodName = listOf(
        MenuItem("Chocolate Cake", "1200 Rs", "Delicious cake with icing", R.drawable.img1),
        MenuItem("Vanilla Cake", "1150 Rs", "Tasty pastry with filling", R.drawable.vanilla),
        MenuItem("Cheese Cake", "1180 Rs", "Freshly baked bread", R.drawable.img3),
        MenuItem("Strawberry Cake", "1250 Rs", "Crunchy chocolate chip cookie", R.drawable.img),
        MenuItem("Muffin", "1280 Rs", "Blueberry muffin", R.drawable.img2),
        MenuItem("Pastry", "180 Rs", "Blueberry muffin", R.drawable.images110)
    )

    private val filteredMenuFoodName = mutableListOf<MenuItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSerachBinding.inflate(inflater, container, false)

        adapter = MenuAdapter(filteredMenuFoodName) { item ->
            addToCart(item)
        }
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        setupSearchView()

        return binding.root
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItems(newText)
                return true
            }
        })


        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }
    }

    private fun filterMenuItems(query: String?) {
        filteredMenuFoodName.clear()
        query?.let {
            originalMenuFoodName.forEach { menuItem ->
                if (menuItem.foodname?.contains(it, ignoreCase = true) == true) {
                    filteredMenuFoodName.add(menuItem)
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun addToCart(item: MenuItem) {

        Toast.makeText(requireContext(), "${item.foodname} added to cart", Toast.LENGTH_SHORT).show()
    }
}
