package kg.hello.hello_sell.data.network.model

import kg.hello.hello_sell.data.network.FirebaseService

class SomeRepository(private val firebaseService: FirebaseService) {
    suspend fun getSomeList() = firebaseService.getSomeList()
}