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

        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_cart, findViewById(R.id.activity_container), true)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_cart_recyclerview)
        var totalPriceTextView = findViewById<TextView>(R.id.activity_cart_total_price_textview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        cartAdapter = CartAdapter(CartManager.getCartItems(), this@CartActivity)
        runBlocking {
            recyclerView.adapter = cartAdapter
            CartManager.cartItemsLiveData.observe(this@CartActivity, Observer { updatedCartItems ->
                cartAdapter.submitList(updatedCartItems)
            })
            CartManager.cartTotalPrice.observe(this@CartActivity, Observer { totalPrice ->
                totalPriceTextView.text = "Prix total: " + String.format("%.2f", totalPrice) + "€"
            })
        }
    }
}