package kg.hello.hello_sell.data.network

import com.google.firebase.firestore.FirebaseFirestore
import kg.hello.hello_sell.data.network.model.Some
import kotlinx.coroutines.tasks.await
import kg.hello.hello_sell.data.network.model.toSome
import kg.hello.hello_sell.data.network.product.model.Product
import kg.hello.hello_sell.data.network.product.model.ProductWithoutId

class FirebaseService {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getSomeList() : List<Some>{
        return db.collection("tests").get().await().documents.map { it.toSome() }
    }

    suspend fun addProduct(product: ProductWithoutId): String{
        return db.collection("product").add(product).await().id
    }

    suspend fun getProductList() : List<Product>{
        return db.collection("product").get().await().documents.map { it.toObject(Product::class.java) ?: Product() }
    }

    suspend fun getProduct(productId: String): Product {
        return db.collection("product").document(productId).get().await().toObject(Product::class.java)!!
    }
}