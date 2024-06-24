package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakeboutique.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private var cartItems: MutableList<CartItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        updateTotalBill()

        binding.btnCheckout.setOnClickListener {
            val totalBill = calculateTotalBill()

            val intent = Intent(requireContext(), CheckoutActivity::class.java).apply {
                putExtra("TOTAL_BILL", totalBill.toString())
                putParcelableArrayListExtra("CART_ITEMS", ArrayList(cartItems))
            }
            startActivity(intent)
        }

    }

    private fun setupRecyclerView() {
        cartItems = getCartItemsFromManager()

        cartAdapter = CartAdapter(
            cartItems,
            { position, newQuantity ->
                updateCartItemQuantity(position, newQuantity)
            },
            { position ->
                removeCartItem(position)
            }
        )

        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
    }

    private fun getCartItemsFromManager(): MutableList<CartItem> {
        val items = mutableListOf<CartItem>()
        val cartItemNames = CartManager.getCartItems()
        val cartItemPrices = CartManager.getCartItemPrices()
        val cartImages = CartManager.getCartImages()

        for (i in cartItemNames.indices) {
            items.add(CartItem(cartItemNames[i], cartItemPrices[i], cartImages[i], 1))
        }

        return items
    }

    private fun updateCartItemQuantity(position: Int, newQuantity: Int) {
        cartItems[position].quantity = newQuantity
        cartAdapter.notifyItemChanged(position)
        updateTotalBill()
    }

    private fun removeCartItem(position: Int) {
        cartItems.removeAt(position)
        cartAdapter.notifyItemRemoved(position)
        updateTotalBill()
    }

    private fun updateTotalBill() {
        val totalBill = calculateTotalBill()
        binding.totalBillTextView.text = "Total: ${totalBill} Rs"
    }

    private fun calculateTotalBill(): Int {
        var total = 0
        for (item in cartItems) {
            total += item.price.removeSuffix(" Rs").toInt() * item.quantity
        }
        return total
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }}