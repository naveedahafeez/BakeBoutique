package com.example.bakeboutique
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakeboutique.databinding.ActivityViewItemsBinding

class ViewItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.itemsRecyclerView.adapter = ItemsAdapter(getItems())
    }
    private fun getItems(): List<Item> {
        return listOf(
            Item("Item 1", "Description 1", 10.0, R.drawable.images82),
            Item("Item 2", "Description 2", 15.0, R.drawable.images84),
            Item("Item 3", "Description 3", 20.0, R.drawable.images112)
        )
    }
}
