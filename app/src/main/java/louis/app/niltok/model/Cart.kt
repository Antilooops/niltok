package louis.app.niltok.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

object CartManager {
    private val _cartItemsLiveData = MutableLiveData<List<CartItem>>(emptyList())
    val cartItemsLiveData: LiveData<List<CartItem>> = _cartItemsLiveData
    val cartItemCount: LiveData<Int> = cartItemsLiveData.map { cartList ->
        cartList.sumOf { it.quantity }
    }
    val cartTotalPrice: LiveData<Float> = cartItemsLiveData.map { cartList ->
        cartList.sumOf { it.product.price.toDouble() * it.quantity }.toFloat()
    }

    private fun getCurrentMutableCart(): MutableList<CartItem> {
        return _cartItemsLiveData.value?.toMutableList() ?: mutableListOf()
    }

    fun addItem(product: Product) {
        val currentCart = getCurrentMutableCart()
        val existingItem = currentCart.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            currentCart.add(CartItem(product, 1))
        }
        _cartItemsLiveData.value = currentCart
    }

    fun removeItem(product: Product) {
        val currentCart = getCurrentMutableCart()
        val targetItem = currentCart.find { it.product.id == product.id}
        if (targetItem != null) {
            if (targetItem.quantity > 1) {
                targetItem.quantity -= 1
            } else {
                currentCart.remove(targetItem)
            }
        }
        _cartItemsLiveData.value = currentCart
    }

    fun removeAllItem(productId: Int) {
        val currentCart = getCurrentMutableCart()
        val initialSize = currentCart.size
        currentCart.removeAll { it.product.id == productId }
        if (currentCart.size != initialSize) {
            _cartItemsLiveData.value = currentCart
        }
    }

    fun getCartItems(): List<CartItem> = _cartItemsLiveData.value.orEmpty()

    fun clearCart() {
        _cartItemsLiveData.value = emptyList()
    }

    fun getTotalPrice(): Float {
        return _cartItemsLiveData.value.orEmpty().sumOf{ it.product.price.toDouble() * it.quantity }.toFloat()
    }
}