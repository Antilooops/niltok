package louis.app.niltok.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.activity.ProductDetailActivity
import louis.app.niltok.activity.click
import louis.app.niltok.model.Product

const val PRODUCT_DATA = "product.data"

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_holder, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val (_, _, type) = products[position]
        val product = products[position]
        val itemView = holder.itemView
        itemView.findViewById<TextView>(R.id.product_name_textview).apply {
            text = product.title
        }
        itemView.findViewById<TextView>(R.id.product_price_textview).apply {
            text = product.price.toString() + " €"
        }

        itemView.findViewById<ImageView>(R.id.product_imageview).let {
            if(product.image.isBlank()) {
                Glide.with(itemView).load(R.drawable.image_product_not_found).into(it)
            } else {
                Glide.with(itemView).load(product.image).into(it)
            }
        }

        itemView.click {
            with(itemView.context) {
                val intent = Intent(this, ProductDetailActivity::class.java).apply {
                    // Pour passer un objet d'une activité à une autre
                    putExtra(PRODUCT_DATA, product)
                }
                startActivity(intent)
            }
        }

    }
}
