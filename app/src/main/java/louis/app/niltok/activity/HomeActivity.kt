package louis.app.niltok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import louis.app.niltok.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val shopButton = findViewById<Button>(R.id.Shop_button)
        val qrscanButton = findViewById<Button>(R.id.QR_button)
        val cartButton = findViewById<ImageButton>(R.id.cart_imageButton)


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
    }

}

fun View.click(action : (View) -> Unit){
    setOnClickListener(action)
    Log.d("Niltok", "click !")
}