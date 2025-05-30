package louis.app.niltok.activity

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.CartAdapter
import louis.app.niltok.model.CartManager

class CartActivity : NavActivity() {
    private lateinit var cartAdapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        // super.onCreate(savedInstanceState)
        // layoutInflater.inflate(R.layout.activity_cart, findViewById(R.id.activity_container), true)

        // val recyclerView = findViewById<RecyclerView>(R.id.activity_cart_recyclerview)

        // recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // runBlocking {
        //     recyclerView.adapter = CartAdapter(CartManager.getCartItems(), this@CartActivity)
        // }
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_cart, findViewById(R.id.activity_container), true)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_cart_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Initialize adapter with an empty list initially or the current cart items
        // The LiveData observer will update it
        cartAdapter = CartAdapter(CartManager.getCartItems(), this@CartActivity)
        runBlocking {
            recyclerView.adapter = cartAdapter
            // Observe changes in cartItemsLiveData
            CartManager.cartItemsLiveData.observe(this@CartActivity, Observer { updatedCartItems ->
                // When cartItemsLiveData changes, submit the new list to the adapter
                cartAdapter.submitList(updatedCartItems)
            })
        }
    }
}