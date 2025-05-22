package louis.app.niltok.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import louis.app.niltok.service.Product

const val PRODUCT_DATA = "product.data"

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {}