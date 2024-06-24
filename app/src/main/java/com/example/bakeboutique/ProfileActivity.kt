package com.example.bakeboutique

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.profileName.text = "Naveeda Hafeez"
        binding.profileEmail.text = "naveedahafeez05@example.com"
        binding.profilePhone.text = "Phone: +92 XXX XXXXXXX"
        binding.profilePassword.text = "Password: *********"

        binding.editProfileButton.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))

        }
    }
}
