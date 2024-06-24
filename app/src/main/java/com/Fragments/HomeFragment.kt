package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bakeboutique.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupImageSlider()
        setupPopularRecyclerView()
        setupViewAllClickListener()
    }

    private fun setupImageSlider() {
        val imageList = ArrayList<SlideModel>().apply {
            add(SlideModel(R.drawable.specailbaner, ScaleTypes.FIT))
            add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))
            add(SlideModel(R.drawable.banner4, ScaleTypes.FIT))
        }

        binding.imageSlider.apply {
            setImageList(imageList, ScaleTypes.FIT)
            setItemClickListener(object : ItemClickListener {
                override fun doubleClick(position: Int) {}

                override fun onItemSelected(position: Int) {
                    val itemMessage = "Selected Image $position"
                    Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setupPopularRecyclerView() {
        val foodNames = listOf(
            "Strawberry Special", "Pastry", "Birthday Cake", "Strawberry Pastry",
            "Brownie", "Cupcake", "Chocolate Cake", "Ceremony Cake", "Strawberry Cake"
        )
        val prices = listOf(
            "1200 Rs", "200 Rs", "1200 Rs", "200 Rs", "500 RS", "100 RS", "3000 RS", "10000 RS", "1200 RS"
        )
        val popularFoodImages = listOf(
            R.drawable.images111, R.drawable.images80, R.drawable.images83,
            R.drawable.images110, R.drawable.images78, R.drawable.images84,
            R.drawable.images82, R.drawable.images87, R.drawable.images112
        )

        popularAdapter = PopularAdapter(foodNames, popularFoodImages, prices) { position ->
            addToCart(position)
        }

        binding.popularRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = popularAdapter
        }
    }

    private fun addToCart(position: Int) {
        val selectedItem = popularAdapter.items[position]
        val selectedImage = popularAdapter.images[position]
        val selectedPrice = popularAdapter.prices[position]


        CartManager.addToCart(selectedItem, selectedPrice, selectedImage)

        Toast.makeText(requireContext(), "${selectedItem} added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun setupViewAllClickListener() {
        binding.viewAll.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(childFragmentManager, "MenuBottomSheetFragment")
        }
    }
}