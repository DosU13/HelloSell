package kg.hello.hello_sell.data.network.product.model

data class Product(
    val id: String = "",
    val name: String = "",
    val quantity: Long = 0L,
    val cost: Double = 0.0,
    val sellPrice: Double = 0.0,
    val image: String = ""
)