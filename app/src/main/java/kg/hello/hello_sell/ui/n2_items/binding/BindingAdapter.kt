package kg.hello.hello_sell.ui.n2_items.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.hello.hello_sell.ui.n2_items.viewmodel.ProductViewModel

@BindingAdapter("productViewModels")
fun bindProductViewModels(recyclerView: RecyclerView, productViewModels: List<ProductViewModel>?){
    Log.e("Here ==>> ", "productViewModels bound: ${productViewModels!!.size}")
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(productViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): ProductRecyclerViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is ProductRecyclerViewAdapter){
        recyclerView.adapter as ProductRecyclerViewAdapter
    }else{
        val productRecyclerViewAdapter = ProductRecyclerViewAdapter()
        recyclerView.adapter = productRecyclerViewAdapter
        productRecyclerViewAdapter
    }
}