package kg.hello.hello_sell.ui.n2_items.viewmodel

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import kg.hello.hello_sell.R
import kg.hello.hello_sell.data.network.product.model.Product

class ProductViewModel(val name: String): ViewModel() {
    @get:LayoutRes
    val layoutId: Int = R.layout.fragment_item_product
    val viewType: Int get() = 0
}