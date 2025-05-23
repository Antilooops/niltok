package louis.app.niltok.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.ProductAdapter
import louis.app.niltok.model.Product

class ShopActivity : NavActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_shop)
        layoutInflater.inflate(R.layout.activity_shop, findViewById(R.id.activity_container), true)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_shop_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        runBlocking {
            val products = Product.getProduct()
            recyclerView.adapter = ProductAdapter(products)
        }
    }
}