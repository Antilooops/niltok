package louis.app.niltok.model

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import louis.app.niltok.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "Product"

enum class Type{
    NO_TYPE, MENS_CLOTHING, JEWELERY, ELECTRONICS, WOMENS_CLOTHING
}

@Parcelize
data class Product(val id: Int, val title: String, val price: Float, val description: String, val category: Type, val image: String, val rate: Float, val count: Int) : Parcelable {
    companion object Utils{
        suspend fun getProduct() : List<Product> {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com/").addConverterFactory(MoshiConverterFactory.create()).client(client).build()
            val productService = retrofit.create(ProductService::class.java)
            return try {
                val getResponse = productService.loadProduct()
                Log.i(TAG, "getProduct: $getResponse")
                getResponse.map {
                    Product(it.id, it.title, it.price, it.description, getType(it.category), it.image, it.rating.rate, it.rating.count)
                }
            } catch (e: IOException) {
                Log.e(TAG, "Network error", e)
                emptyList()
            }
        }
    }
}

fun getType(input: String) : Type {
    return when (input) {
        "men's clothing" -> Type.MENS_CLOTHING
        "jewelery" -> Type.JEWELERY
        "electronics" -> Type.ELECTRONICS
        "women's clothing" -> Type.WOMENS_CLOTHING
        else -> Type.NO_TYPE
   }
}