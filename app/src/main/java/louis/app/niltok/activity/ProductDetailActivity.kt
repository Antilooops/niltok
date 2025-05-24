package louis.app.niltok.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.adapter.PRODUCT_DATA
import louis.app.niltok.model.Product

class ProductDetailActivity : NavActivity(){
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_detail_product)
        layoutInflater.inflate(R.layout.activity_detail_product, findViewById(R.id.activity_container), true)

        val imageView = findViewById<ImageView>(R.id.product_detail_imageview)
        val titletextView = findViewById<TextView>(R.id.product_name_detail_textview)
        val pricetextView = findViewById<TextView>(R.id.price_product_detail_textview)
        val ratecounttextView = findViewById<TextView>(R.id.rate_count_detail_textview)
        val descriptiontextView = findViewById<TextView>(R.id.description_detail_textView)
        val categorytextView = findViewById<TextView>(R.id.category_product_detail_textview)
        val rateratingbar = findViewById<RatingBar>(R.id.rate_value_detail_ratingbar)

        val extras = intent.extras

        extras?.getParcelable(PRODUCT_DATA, Product::class.java)?.let {
            titletextView.text = it.title
            Glide.with(this).load(it.image).into(imageView)
            pricetextView.text = it.price.toString() + " €"
            ratecounttextView.text = it.count.toString() + " note"
            descriptiontextView.text = it.description
            categorytextView.text = it.category.toString()
            rateratingbar.rating = it.rate
        }
    }

}