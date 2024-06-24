package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityAddressBinding

class Address_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalBill = intent.getStringExtra("TOTAL_BILL") ?: "0 Rs"
        Log.d("AddressActivity", "Received total bill: $totalBill")

        binding.totalBillTextView.text = totalBill

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this, Acitivity_Order_Confirmed::class.java))
        }
    }
}
