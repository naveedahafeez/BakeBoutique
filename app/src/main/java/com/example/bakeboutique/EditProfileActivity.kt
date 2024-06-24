package com.example.bakeboutique
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentName = "Naveeda Hafeez"
        val currentEmail = "naveedahafeez05@example.com"
        val currentPhone = "+92 XXX XXXXXXX"

        binding.editName.setText(currentName)
        binding.editEmail.setText(currentEmail)
        binding.editPhone.setText(currentPhone)

        binding.saveButton.setOnClickListener {

            val newName = binding.editName.text.toString()
            val newEmail = binding.editEmail.text.toString()
            val newPhone = binding.editPhone.text.toString()
            val newPassword = binding.editPassword.text.toString()

            updateProfile(newName, newEmail, newPhone, newPassword)

            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun updateProfile(name: String, email: String, phone: String, password: String) {

        println("Updated Profile:")
        println("Name: $name")
        println("Email: $email")
        println("Phone: $phone")

    }
}
