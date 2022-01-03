package kg.hello.hello_sell.di.module

import kg.hello.hello_sell.ui.main.viewmodel.MainViewModel
import kg.hello.hello_sell.ui.n2_items.add_product.viewmodel.AddProductViewModel
import kg.hello.hello_sell.ui.n2_items.viewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { MainViewModel(get()) }
    viewModel { ProductListViewModel(get()) }
    viewModel { AddProductViewModel(get()) }
}