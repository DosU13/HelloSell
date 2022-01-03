package kg.hello.hello_sell.ui.n2_items

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kg.hello.hello_sell.databinding.FragmentPageProductsBinding
import kg.hello.hello_sell.ui.n2_items.add_product.AddProductActivity
import kg.hello.hello_sell.ui.n2_items.viewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductPageFragment : Fragment() {
    private lateinit var binding: FragmentPageProductsBinding
    private val productListViewModel: ProductListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageProductsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        Log.e("Here ==>> ", "1 recyclerview is created")
        binding.viewModel = productListViewModel
        binding.addProduct.setOnClickListener { addProductClicked() }
        return binding.root
    }

    private val addProductLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.run{
                binding.productRecyclerView.adapter?.notifyDataSetChanged()
//                Log.e("Here", this.toString())
//                val productId = getStringExtra("productId")
//                val productPos = getIntExtra("product_position", -1)
//                if(productPos==-1) productsViewModel.getProducts()
//                else productId?.let { productsViewModel.getProduct(it, productPos) }
//                Log.e("Here", "$productId $productPos")
            }
        }
    }

    private fun addProductClicked(){
        val intent = Intent(context, AddProductActivity::class.java)
        intent.putExtra("isNewProduct", true)
        addProductLauncher.launch(intent)
    }
}