package com.example.bakeboutique
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.btnRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val phone = binding.editTextPhone.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (validateInputs(email, phone, password, confirmPassword)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                saveUserData(userId, email, phone)
                            }
                            Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Login_Screen::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.tvlogin.setOnClickListener {
            startActivity(Intent(this, Login_Screen::class.java))
        }
    }

    private fun validateInputs(email: String, phone: String, password: String, confirmPassword: String): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Enter a valid email address"
            binding.editTextEmail.requestFocus()
            return false
        }

        val phonePattern = "\\+923[0-9]{9}".toRegex()
        if (!phone.matches(phonePattern)) {
            binding.editTextPhone.error = "Enter a valid phone number starting with +92"
            binding.editTextPhone.requestFocus()
            return false
        }

        if (password.length < 8) {
            binding.editTextPassword.error = "Password must be at least 8 characters long"
            binding.editTextPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Passwords do not match"
            binding.editTextConfirmPassword.requestFocus()
            return false
        }

        return true
    }

    private fun saveUserData(userId: String, email: String, phone: String) {
        val databaseRef = database.reference.child("users").child(userId)
        val userData = UserData(email, phone)
        databaseRef.setValue(userData)
    }
}

data class UserData(
    val email: String = "",
    val phone: String = ""
)
