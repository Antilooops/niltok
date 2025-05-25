package louis.app.niltok.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.CartAdapter
import louis.app.niltok.model.CartManager

class CartActivity : NavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_cart, findViewById(R.id.activity_container), true)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_cart_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        runBlocking {
            recyclerView.adapter = CartAdapter(CartManager.getCartItems())
        }
    }
}