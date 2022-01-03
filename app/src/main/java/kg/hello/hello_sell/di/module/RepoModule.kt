package kg.hello.hello_sell.di.module

import kg.hello.hello_sell.data.network.model.SomeRepository
import kg.hello.hello_sell.data.network.product.ProductRepository
import org.koin.dsl.module

val repoModule = module {
    single { SomeRepository(get()) }
    single { ProductRepository(get()) }
}