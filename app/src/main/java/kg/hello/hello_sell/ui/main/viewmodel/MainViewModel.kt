package kg.hello.hello_sell.ui.main.viewmodel

import androidx.lifecycle.*
import kg.hello.hello_sell.data.network.model.SomeRepository
import kg.hello.hello_sell.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val someRepository: SomeRepository): ViewModel() {
    private val _text = MutableLiveData<Resource<String>>()
    val text: LiveData<Resource<String>> get() = _text

    init {
        fetchText()
    }

    private fun fetchText() = viewModelScope.launch{
        _text.postValue(Resource.loading(null))
        try{
            _text.postValue(Resource.success(someRepository.getSomeList().joinToString { "\n"+it.name }))
        }catch (e: Exception){
            _text.postValue(Resource.error(e.localizedMessage ?: "no message", null))
        }
    }
}