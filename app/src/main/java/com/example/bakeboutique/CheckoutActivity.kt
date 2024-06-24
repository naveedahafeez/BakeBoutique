package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityCheckoutBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private var totalBill: Int = 0
    private lateinit var database: DatabaseReference
    private lateinit var cartItems: ArrayList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference


        val totalBillString = intent.getStringExtra("TOTAL_BILL") ?: "0 Rs"
        totalBill = extractNumericPart(totalBillString)
        cartItems = intent.getSerializableExtra("CART_ITEMS") as? ArrayList<CartItem> ?: arrayListOf()


        binding.totalBillTextView.text = "Total: $totalBill"

        binding.checkoutButton.setOnClickListener {
            if (validateForm()) {

                saveOrderToDatabase()
            }
        }
    }

    private fun extractNumericPart(totalBillString: String): Int {

        val numericOnly = totalBillString.replace(Regex("[^\\d]"), "")
        return if (numericOnly.isNotEmpty()) {
            Integer.parseInt(numericOnly)
        } else {
            0
        }
    }

    private fun saveOrderToDatabase() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        val currentDateTime = getCurrentDateTime()

        val orderDetails = OrderDetails(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            totalBill = totalBill,
            dateTime = currentDateTime,
            items = cartItems
        )


        val orderId = database.child("pending_orders").push().key
        if (orderId != null) {
            database.child("pending_orders").child(orderId).setValue(orderDetails)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Order saved successfully")

                        navigateToOrderConfirmed()
                    } else {
                        showToast("Failed to save order")
                    }
                }
        }
    }

    private fun navigateToOrderConfirmed() {
        val intent = Intent(this,Acitivity_Order_Confirmed::class.java).apply {
            putExtra("RECENTLY_BOUGHT_PRODUCTS", cartItems)
        }
        startActivity(intent)
        finish()
    }

    private fun validateForm(): Boolean {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phoneNumber = binding.phoneNumberEditText.text.toString()

        return when {
            firstName.isEmpty() -> {
                showToast("First name is required")
                false
            }
            lastName.isEmpty() -> {
                showToast("Last name is required")
                false
            }
            email.isEmpty() -> {
                showToast("Email is required")
                false
            }
            phoneNumber.isEmpty() -> {
                showToast("Phone number is required")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}
