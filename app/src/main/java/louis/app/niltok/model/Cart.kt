package louis.app.niltok.model

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }

    fun removeItem(productId: Int) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun clearCart() {
        cartItems.clear()
    }

    fun getTotalPrice(): Float {
        return cartItems.sumOf{ it.product.price.toDouble() * it.quantity }.toFloat()
    }
}
