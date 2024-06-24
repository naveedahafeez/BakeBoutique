package com.example.bakeboutique

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.model.MenuItem

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<MenuItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<MenuItem>> get() = _cartItems

    fun addItemToCart(item: MenuItem) {
        _cartItems.value?.let {
            it.add(item)
            _cartItems.value = it
        }
    }

    fun removeItemFromCart(item: MenuItem) {
        _cartItems.value?.let {
            it.remove(item)
            _cartItems.value = it
        }
    }

    fun updateItemQuantity(item: MenuItem, quantity: Int) {
        _cartItems.value?.let {
            val index = it.indexOf(item)
            if (index != -1) {
                it[index].quantity = quantity
                _cartItems.value = it
            }
        }
    }
}
