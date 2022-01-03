package kg.hello.hello_sell.data.network.product

import kg.hello.hello_sell.data.network.FirebaseService
import kg.hello.hello_sell.data.network.product.model.Product
import kg.hello.hello_sell.data.network.product.model.ProductWithoutId
import kg.hello.hello_sell.utils.BaseRepository
import kg.hello.hello_sell.utils.Resource

class ProductRepository(private val firebaseService: FirebaseService) : BaseRepository() {
    suspend fun addProduct(product: ProductWithoutId) : String = firebaseService.addProduct(product)
    suspend fun getProductList(): Resource<List<Product>> = safeApiCall { firebaseService.getProductList()}
    suspend fun getProduct(productId: String) = firebaseService.getProduct(productId)
}