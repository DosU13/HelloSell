package kg.hello.hello_sell.ui.n2_items.binding

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kg.hello.hello_sell.BR
import kg.hello.hello_sell.R
import kg.hello.hello_sell.ui.n2_items.viewmodel.ProductViewModel

class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {
    private var productViewModels: List<ProductViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.fragment_item_product, parent, false)
        Log.e("HERE ==>> ", "main adapter created: ${binding.javaClass}")
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("HERE ==>> ", "binding view holder: $position")
        holder.bind(productViewModels[position])
    }

    override fun getItemCount(): Int = productViewModels.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<ProductViewModel>?){
        productViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productViewModel: ProductViewModel){
            binding.setVariable(BR.productViewModel, productViewModel)
        }
    }

}