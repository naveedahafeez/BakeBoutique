package com.example.bakeboutique

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductPageActivity : AppCompatActivity() {

    private lateinit var imgProduct: ImageView
    private lateinit var tvProductTitle: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var btnDecrease: Button
    private lateinit var tvQuantity: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnAddToCart: Button
    private lateinit var tvProductDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        imgProduct = findViewById(R.id.imgProduct)
        tvProductTitle = findViewById(R.id.tvProductTitle)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        btnDecrease = findViewById(R.id.btnDecrease)
        tvQuantity = findViewById(R.id.tvQuantity)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        tvProductDescription = findViewById(R.id.tvProductDescription)

        btnDecrease.setOnClickListener {
            val quantity = tvQuantity.text.toString().toInt()
            if (quantity > 1) {
                tvQuantity.text = (quantity - 1).toString()
            }
        }

        btnIncrease.setOnClickListener {
            val quantity = tvQuantity.text.toString().toInt()
            tvQuantity.text = (quantity + 1).toString()
        }

        btnAddToCart.setOnClickListener {

        }
    }
}
