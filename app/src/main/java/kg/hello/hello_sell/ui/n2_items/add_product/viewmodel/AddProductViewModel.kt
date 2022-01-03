package kg.hello.hello_sell.ui.n2_items.add_product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.hello.hello_sell.data.network.product.ProductRepository
import kg.hello.hello_sell.data.network.product.model.Product
import kg.hello.hello_sell.data.network.product.model.ProductWithoutId
import kg.hello.hello_sell.utils.Resource
import kotlinx.coroutines.launch

class AddProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    fun fetchProduct(productId: String) : LiveData<Resource<Product>>{
        val result = MutableLiveData<Resource<Product>>()
        viewModelScope.launch {
            result.postValue(Resource.loading(null))
            try {
                result.postValue(Resource.success(productRepository.getProduct(productId)))
            } catch (e: Exception) {
                result.postValue(Resource.error(e.localizedMessage ?: "no message", null))
            }
        }
        return result
    }

    fun addProduct(product: ProductWithoutId): LiveData<Resource<String>> {
        val result = MutableLiveData<Resource<String>>()
        viewModelScope.launch{
            result.postValue(Resource.loading(null))
            try{
                result.postValue(Resource.success(productRepository.addProduct(product)))
            }catch (e: Exception){
                result.postValue(Resource.error(e.localizedMessage ?: "no message", null))
            }
        }
        return result
    }
}