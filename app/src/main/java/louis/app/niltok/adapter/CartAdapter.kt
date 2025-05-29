package louis.app.niltok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.model.CartItem


class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameCartItemText = itemView.findViewById<TextView>(R.id.cart_item_holder_name_textview)
    val detailsCartItemText = itemView.findViewById<TextView>(R.id.cart_item_holder_details_textview)
    val imageView = itemView.findViewById<ImageView>(R.id.cart_item_holder_imageview)
}

class CartAdapter(val cartItems: List<CartItem>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_holder, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartItems.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.nameCartItemText.text = cartItem.product.title
        holder.detailsCartItemText.text = cartItem.quantity.toString() + " x " + cartItem.product.price.toString() + "€"
        holder.imageView.let {
            if(cartItem.product.image.isBlank()) {
                Glide.with(holder.itemView).load(R.drawable.image_product_not_found).into(it)
            } else {
                Glide.with(holder.itemView).load(cartItem.product.image).into(it)
            }
        }
    }
}
