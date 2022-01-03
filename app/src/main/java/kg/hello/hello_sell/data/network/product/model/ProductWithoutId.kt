package kg.hello.hello_sell.data.network.product.model

data class ProductWithoutId(
    val name: String,
    val quantity: Long,
    val cost: Double,
    val sellPrice: Double,
    val image: String = ""
)