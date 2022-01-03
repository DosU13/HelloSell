package kg.hello.hello_sell.ui.n2_items.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.hello.hello_sell.data.network.product.ProductRepository
import kg.hello.hello_sell.data.network.product.model.Product
import kg.hello.hello_sell.utils.Resource
import kotlinx.coroutines.launch

class ProductListViewModel(private val productRepository: ProductRepository): ViewModel() {
    val data: LiveData<List<ProductViewModel>> get() = _data
    private val _data = MutableLiveData<List<ProductViewModel>>(emptyList())

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch{
            Log.e("Here ==>> ", "firebase launched")
            val res = productRepository.getProductList()
            Log.e("HERE ==>> ", "firebase status: ${res.status}")
            val productList = if(res.status == Resource.Status.SUCCESS) res.data ?: emptyList() else emptyList()
            Log.e("Here ==>> ", "firebase returned: ${productList.size}")
            val viewData = createViewData(productList)
            _data.postValue(viewData)
        }
    }

    private fun createViewData(productList: List<Product>): List<ProductViewModel> {
        return productList.map { ProductViewModel(it.name) }
    }
}