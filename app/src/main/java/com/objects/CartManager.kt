package com.example.bakeboutique

object CartManager {
    private val cartItems = mutableListOf<String>()
    private val cartItemPrices = mutableListOf<String>()
    private val cartImages = mutableListOf<Int>()

    fun addToCart(itemName: String, price: String, imageResId: Int) {
        cartItems.add(itemName)
        cartItemPrices.add(price)
        cartImages.add(imageResId)
    }

    fun getCartItems(): List<String> {
        return cartItems
    }

    fun getCartItemPrices(): List<String> {
        return cartItemPrices
    }

    fun getCartImages(): List<Int> {
        return cartImages
    }


}
