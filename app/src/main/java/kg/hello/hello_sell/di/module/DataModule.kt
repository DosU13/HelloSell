package kg.hello.hello_sell.di.module

import kg.hello.hello_sell.data.network.FirebaseService
import org.koin.dsl.module

val dataModule = module {
    single {
        FirebaseService()
    }
}