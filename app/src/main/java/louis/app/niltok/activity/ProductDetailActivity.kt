package louis.app.niltok.activity

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
        val qrgenerateimagebutton = findViewById<ImageButton>(R.id.qr_code_detail_imageButton)
        val qrbackbutton = findViewById<Button>(R.id.detail_qr_back_button)
        val qrcontainer = findViewById<ConstraintLayout>(R.id.qr_container)
        val qrimageview = findViewById<ImageView>(R.id.qr_code_imageview)
        val qroverlay = findViewById<View>(R.id.qr_overlay)

        val extras = intent.extras

        val product = extras?.getParcelable(PRODUCT_DATA, Product::class.java)
        product?.let {
            titletextView.text = it.title
            Glide.with(this).load(it.image).into(imageView)
            pricetextView.text = it.price.toString() + " €"
            ratecounttextView.text = it.count.toString() + " note"
            descriptiontextView.text = it.description
            categorytextView.text = it.category.toString()
            rateratingbar.rating = it.rate
        }

        qrgenerateimagebutton.click {
            product?.let {
                val qrUrl = "https://quickchart.io/qr?text=${Uri.encode(it.id.toString())}&size=200&caption=${Uri.encode(
                    it.category.toString()
                )}&captionFontFamily=mono&captionFontSize=10"
                Glide.with(this).load(qrUrl).into(qrimageview)
                qrcontainer.visibility = View.VISIBLE
                qroverlay.visibility = View.VISIBLE
            }
        }

        qrbackbutton.click {
            qroverlay.visibility = View.GONE
            qrcontainer.visibility = View.GONE
        }
    }

}