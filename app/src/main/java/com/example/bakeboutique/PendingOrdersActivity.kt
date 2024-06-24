package com.example.bakeboutique


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PendingOrdersActivity : AppCompatActivity() {

    private lateinit var pendingOrdersListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_orders)


        pendingOrdersListView = findViewById(R.id.pending_orders_listview)


        val pendingOrders = listOf("Order #1", "Order #2", "Order #3", "Order #4", "Order #5")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pendingOrders)
        pendingOrdersListView.adapter = adapter


        pendingOrdersListView.setOnItemLongClickListener { parent, view, position, id ->

            val orderToDelete = pendingOrders[position]

            adapter.remove(orderToDelete)
            true
        }
    }
}
