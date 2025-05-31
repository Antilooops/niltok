package louis.app.niltok.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    // suspend fun loadProduct(@Query("limit") count: Int = 10) : GetProductsResponse
    suspend fun loadProduct() : List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int) : Product
}

// data class GetProductsResponse(val results : List<Product>)
data class Product(val id: Int, val title: String, val price: Float, val description: String, val category: String, val image: String, val rating: Rating)
data class Rating(val rate: Float, val count: Int)