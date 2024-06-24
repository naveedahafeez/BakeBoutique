package com.example.bakeboutique

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Acitivity_Order_Confirmed: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_order_confirmed)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Home_Page::class.java))
            finish()
        }, 3000)
    }
}
