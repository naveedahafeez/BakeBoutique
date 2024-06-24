package com.example.bakeboutique

data class OrderDetails(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val totalBill: Int = 0,
    val dateTime: String = "",
    val items: List<CartItem> = emptyList()
)
