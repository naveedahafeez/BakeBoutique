package com.example.bakeboutique
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bakeboutique.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addUserButton.setOnClickListener {

        }
    }
}
