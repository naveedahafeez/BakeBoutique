package com.example.bakeboutique
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class AdminSignupActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)


        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.signup_button)


        signupButton.setOnClickListener {
            if (validateInput()) {

                val username = usernameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                navigateToLogin()
            } else {

                Snackbar.make(signupButton, "Invalid input, please check again.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(): Boolean {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()


        return username.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && password == confirmPassword
    }

    private fun navigateToLogin() {

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("username", usernameEditText.text.toString())
        startActivity(intent)
        finish()
    }
}
