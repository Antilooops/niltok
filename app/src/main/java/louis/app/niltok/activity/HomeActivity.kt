package louis.app.niltok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.HorizontalProductAdapter
import louis.app.niltok.adapter.PRODUCT_DATA
import louis.app.niltok.adapter.ProductAdapter
import louis.app.niltok.model.Product
import louis.app.niltok.model.Product.Utils.getProductById

private const val TAG = "Home"

class HomeActivity : AppCompatActivity() {

    lateinit var scanQrButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val shopButton = findViewById<Button>(R.id.Shop_button)
        val qrscanButton = findViewById<Button>(R.id.QR_button)
        val cartButton = findViewById<ImageButton>(R.id.cart_imageButton)
        val horizontalrecyclerview = findViewById<RecyclerView>(R.id.horizontal_recyclerview)

        scanQrButton = findViewById(R.id.QR_button)
        val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                Log.i(TAG, "onCreate: $result")
                val product: Product
                runBlocking {
                    product = getProductById(id = result.contents.toInt())!!
                }
                val intent = Intent(this, louis.app.niltok.activity.ProductDetailActivity::class.java).apply {
                    putExtra(PRODUCT_DATA, product)
                }
                startActivity(intent)
            } else {
                Log.i(TAG, "NO PRODUCT")
            }
        }

        shopButton.click {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        qrscanButton.click {
            val options = ScanOptions()
            options.setPrompt("Scanner un QR code")
            options.setBeepEnabled(true)
            options.setOrientationLocked(true)
            options.setCaptureActivity(CaptureActivity::class.java)
            barcodeLauncher.launch(options)

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