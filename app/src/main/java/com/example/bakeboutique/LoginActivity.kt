package com.example.bakeboutique
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {

            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        binding.signupLink.setOnClickListener {
            startActivity(Intent(this, AdminSignupActivity::class.java))
        }

    }
}
