package com.example.bakeboutique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakeboutique.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val recentlyBoughtProducts = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecentlyBoughtProductAdapter(recentlyBoughtProducts, requireContext())
        binding.recentlyBoughtRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recentlyBoughtRecyclerView.adapter = adapter

        populateRecentlyBoughtProducts()
    }

    private fun populateRecentlyBoughtProducts() {

        recentlyBoughtProducts.add(Product("Ceremony Cake", "10000Rs", R.drawable.images87))
        recentlyBoughtProducts.add(Product("Chocolate Cake", "1200Rs", R.drawable.images82))
        recentlyBoughtProducts.add(Product("Strawberry Pastry", "200Rs", R.drawable.images110))


        binding.recentlyBoughtRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
