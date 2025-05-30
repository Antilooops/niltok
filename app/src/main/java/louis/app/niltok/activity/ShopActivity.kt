package louis.app.niltok.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.ProductAdapter
import louis.app.niltok.model.CartManager
import louis.app.niltok.model.Product

class ShopActivity : NavActivity()  {

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_shop, findViewById(R.id.activity_container), true)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_shop_recyclerview)
        val searchView =findViewById<SearchView>(R.id.search_view)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        runBlocking {
            val adapter = ProductAdapter(Product.getProduct()) { product ->
                CartManager.addItem(product)
                Toast.makeText(this@ShopActivity, "${product.title} added to cart", Toast.LENGTH_SHORT).show()
            }
            recyclerView.adapter = adapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.filter(query.orEmpty())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter(newText.orEmpty())
                    return true
                }
            })

        }
    }
}
