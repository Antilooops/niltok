package louis.app.niltok.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.model.Product
import louis.app.niltok.activity.click

const val PRODUCT_DATA = "product.data"

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameProductText = itemView.findViewById<TextView>(R.id.product_holder_name_textview)
    val priceProductText = itemView.findViewById<TextView>(R.id.product_holder_price_textview)
    val addToCartImageButton = itemView.findViewById<ImageButton>(R.id.add_item_to_cart_imagebutton)
    val imageView = itemView.findViewById<ImageView>(R.id.product_holder_imageview)
}

class ProductAdapter(val products: List<Product>, private val onAddToCartClick: (Product) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {
    private var filteredList: List<Product> = products.toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_holder, parent, false)
        return ProductViewHolder(view)
    }

//    override fun getItemCount() = products.size

    override fun getItemCount() = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            products
        } else {
            products.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product: Product = products[position]
        val product = filteredList[position]
        holder.nameProductText.text = product.title
        holder.priceProductText.text = product.price.toString()
        holder.imageView.let {
            if(product.image.isBlank()) {
                Glide.with(holder.itemView).load(R.drawable.image_product_not_found).into(it)
            } else {
                Glide.with(holder.itemView).load(product.image).into(it)
            }
        }

        holder.addToCartImageButton.setOnClickListener {
            onAddToCartClick(product)
        }
        holder.itemView.click {
            with(holder.itemView.context) {
                val intent = Intent(this, louis.app.niltok.activity.ProductDetailActivity::class.java).apply {
                    putExtra(PRODUCT_DATA, product)
                }
                startActivity(intent)
            }
        }
    }
}
