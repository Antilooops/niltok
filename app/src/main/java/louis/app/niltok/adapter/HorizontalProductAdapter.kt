package louis.app.niltok.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.activity.click
import louis.app.niltok.model.Product

class HorizontalProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.findViewById<ImageView>(R.id.horizontal_product_holder_imageview)
}

class HorizontalProductAdapter(val products: List<Product>) : RecyclerView.Adapter<HorizontalProductViewHolder>() {
    private var productList: List<Product> = products.toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_holder_horizontal, parent, false)
        return HorizontalProductViewHolder(view)
    }

    override fun getItemCount() = productList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HorizontalProductViewHolder, position: Int) {
        val product = productList[position]

        holder.imageView.let {
            if (product.image.isBlank()) {
                Glide.with(holder.itemView).load(R.drawable.image_product_not_found).into(it)
            } else {
                Glide.with(holder.itemView).load(product.image).into(it)
            }
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