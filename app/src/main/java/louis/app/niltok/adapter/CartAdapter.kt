package louis.app.niltok.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import louis.app.niltok.R
import louis.app.niltok.model.CartItem
import louis.app.niltok.model.CartManager


class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameCartItemText = itemView.findViewById<TextView>(R.id.cart_item_holder_name_textview)
    val detailsCartItemText = itemView.findViewById<TextView>(R.id.cart_item_holder_details_textview)
    val imageView = itemView.findViewById<ImageView>(R.id.cart_item_holder_imageview)
    val increaseCartItem = itemView.findViewById<ImageButton>(R.id.cart_item_holder_increase_imagebutton)
    val decreaseCartItem = itemView.findViewById<ImageButton>(R.id.cart_item_holder_decrease_imagebutton)
}

class CartAdapter(val initialCartItems: List<CartItem>, private val context: Context) : RecyclerView.Adapter<CartViewHolder>() {
    private var currentCartItems: MutableList<CartItem> = initialCartItems.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_holder, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = currentCartItems.size

    fun submitList(newList: List<CartItem>) {
        currentCartItems.clear()
        currentCartItems.addAll(newList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = currentCartItems[position]
        holder.nameCartItemText.text = cartItem.product.title
        holder.detailsCartItemText.text = cartItem.quantity.toString() + " x " + cartItem.product.price.toString() + "€"
        holder.imageView.let {
            if(cartItem.product.image.isBlank()) {
                Glide.with(holder.itemView).load(R.drawable.image_product_not_found).into(it)
            } else {
                Glide.with(holder.itemView).load(cartItem.product.image).into(it)
            }
        }
        holder.increaseCartItem.setOnClickListener {
            CartManager.addItem(cartItem.product)
            notifyItemChanged(position)
            Toast.makeText(context, "${cartItem.product.title} added to cart", Toast.LENGTH_SHORT).show()
        }
        holder.decreaseCartItem.setOnClickListener {
            val productToRemove = cartItem.product
            CartManager.removeItem(productToRemove)
            val latestCartFromManager = CartManager.getCartItems()
            val itemInLatestCart = latestCartFromManager.find { it.product.id == productToRemove.id }
            val currentAdapterPosition = currentCartItems.indexOfFirst { it.product.id == productToRemove.id }
            if (currentAdapterPosition != -1) {
                if (itemInLatestCart != null && itemInLatestCart.quantity > 0) {
                    currentCartItems[currentAdapterPosition] = itemInLatestCart
                    notifyItemChanged(currentAdapterPosition)
                } else {
                    currentCartItems.removeAt(currentAdapterPosition)
                    notifyItemRemoved(currentAdapterPosition)
                }
            }
            Toast.makeText(context, "${productToRemove.title} removed from cart", Toast.LENGTH_SHORT).show()
        }
    }
}
