package louis.app.niltok.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.model.Product

private const val TAG = "Main"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        runBlocking {
            val products = Product.getProduct()
            for (product in products) {
                Log.i(TAG, "${product.id} : ${product.title}")
            }
        }
    }
}