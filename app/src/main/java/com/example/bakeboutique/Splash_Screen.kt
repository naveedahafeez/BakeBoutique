package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(
                this@Splash_Screen,
                MainScreen::class.java
            )
            startActivity(intent)

            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {

        private const val SPLASH_TIME_OUT = 4000
    }
}
