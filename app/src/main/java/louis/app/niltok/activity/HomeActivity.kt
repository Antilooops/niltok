package louis.app.niltok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.HorizontalProductAdapter
import louis.app.niltok.adapter.ProductAdapter
import louis.app.niltok.model.Product

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val shopButton = findViewById<Button>(R.id.Shop_button)
        val qrscanButton = findViewById<Button>(R.id.QR_button)
        val cartButton = findViewById<ImageButton>(R.id.cart_imageButton)
        val horizontalrecyclerview = findViewById<RecyclerView>(R.id.horizontal_recyclerview)


        shopButton.click {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        qrscanButton.click {
            val intent = Intent(this, QRScanActivity::class.java)
            startActivity(intent)
        }

        cartButton.click {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        runBlocking {
            val products = Product.getProduct()
            val randomProducts = products.shuffled().take(10)
            horizontalrecyclerview.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            horizontalrecyclerview.adapter = HorizontalProductAdapter(randomProducts)
        }
    }

}

fun View.click(action : (View) -> Unit){
    setOnClickListener(action)
    Log.d("Niltok", "click !")
}