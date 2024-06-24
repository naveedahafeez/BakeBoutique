package com.example.bakeboutique
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CompletedOrdersActivity : AppCompatActivity() {

    private lateinit var completedOrdersListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_orders)
        completedOrdersListView = findViewById(R.id.completed_orders_listview)
        val completedOrders = listOf("Order #6", "Order #7", "Order #8", "Order #9", "Order #10")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, completedOrders)
        completedOrdersListView.adapter = adapter

        completedOrdersListView.setOnItemLongClickListener { parent, view, position, id ->

            val orderToDelete = completedOrders[position]
            adapter.remove(orderToDelete)
            true
        }
    }
}
