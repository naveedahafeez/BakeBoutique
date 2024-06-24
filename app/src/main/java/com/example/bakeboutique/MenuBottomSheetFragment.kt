package com.example.bakeboutique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.MenuAdapter
import com.example.bakeboutique.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.model.MenuItem

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMenuBottomSheetBinding? = null
    private val binding get() = _binding!!


    private val menuItems = mutableListOf(
        MenuItem("Starwbery Specail", "180 Rs", "Blueberry muffin", R.drawable.img4),
        MenuItem("Chocolate Cake", "1200 Rs", "Delicious cake with icing", R.drawable.img1),
        MenuItem("Vanilla Cake", "1150 Rs", "Tasty pastry with filling", R.drawable.vanilla),
        MenuItem("Cheese Cake", "1180 Rs", "Freshly baked bread", R.drawable.img3),
        MenuItem("Starwbery Specail", "180 Rs", "Blueberry muffin", R.drawable.img4),
        MenuItem("Strawberry Cake", "1250 Rs", "Crunchy chocolate chip cookie", R.drawable.img),
        MenuItem("Muffin", "1280 Rs", "Blueberry muffin", R.drawable.img2),
        MenuItem("Pastry", "180 Rs", "Blueberry muffin", R.drawable.images110),
                MenuItem("Chocolate Cake", "1200 Rs", "Delicious cake with icing", R.drawable.img1),
    MenuItem("Vanilla Cake", "1150 Rs", "Tasty pastry with filling", R.drawable.vanilla),
    MenuItem("Cheese Cake", "1180 Rs", "Freshly baked bread", R.drawable.img3),
    MenuItem("Strawberry Cake", "1250 Rs", "Crunchy chocolate chip cookie", R.drawable.img),
    MenuItem("Muffin", "1280 Rs", "Blueberry muffin", R.drawable.img2),
    MenuItem("Pastry", "180 Rs", "Blueberry muffin", R.drawable.images110),
    MenuItem("Chocolate Cake", "1200 Rs", "Delicious cake with icing", R.drawable.img1),
    MenuItem("Vanilla Cake", "1150 Rs", "Tasty pastry with filling", R.drawable.vanilla),
    MenuItem("Cheese Cake", "1180 Rs", "Freshly baked bread", R.drawable.img3),
    MenuItem("Strawberry Cake", "1250 Rs", "Crunchy chocolate chip cookie", R.drawable.img),
    MenuItem("Muffin", "1280 Rs", "Blueberry muffin", R.drawable.img2),
    MenuItem("Pastry", "180 Rs", "Blueberry muffin", R.drawable.images110),
        MenuItem("Muffin", "1280 Rs", "Blueberry muffin", R.drawable.img2),
        MenuItem("Starwbery Specail", "180 Rs", "Blueberry muffin", R.drawable.img4)
    )


    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        menuAdapter = MenuAdapter(menuItems) { item ->
            addToCart(item)
        }
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.menuRecyclerView.adapter = menuAdapter
    }

    private fun addToCart(item: MenuItem) {

        Toast.makeText(requireContext(), "${item.foodname} added to cart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun show(fragmentManager: FragmentManager, tag: String) {
            val fragment = MenuBottomSheetFragment()
            fragment.show(fragmentManager, tag)
        }
    }
}
