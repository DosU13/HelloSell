package kg.hello.hello_sell.ui.n2_items.add_product

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import kg.hello.hello_sell.R
import kg.hello.hello_sell.data.network.product.model.Product
import kg.hello.hello_sell.data.network.product.model.ProductWithoutId
import kg.hello.hello_sell.databinding.ActivityAddProductBinding
import kg.hello.hello_sell.ui.n2_items.add_product.viewmodel.AddProductViewModel
import kg.hello.hello_sell.utils.Resource
import kg.hello.hello_sell.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddProductActivity : AppCompatActivity() {
    private val addProductViewModel: AddProductViewModel by viewModel()
    private lateinit var binding: ActivityAddProductBinding
    private var isNewProduct: Boolean = true
    private lateinit var product: Product
    private var productPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isNewProduct = intent.getBooleanExtra("isNewProduct", true)
        if(!isNewProduct) {
            intent.getStringExtra("productId")?.let { updateUIWithProduct(addProductViewModel.fetchProduct(it))}
            productPos = intent.getIntExtra("product_position", -1)
            binding.addProductComplete.text = getString(R.string.change_product_btn)
        }
        binding.pickImage.setOnClickListener { imageFromGalleryPressed() }
        binding.addProductComplete.setOnClickListener{completeBtnClicked()}
    }

    private fun updateUIWithProduct(fetchProduct: LiveData<Resource<Product>>) {
        fetchProduct.observe(this, { when (it.status) {
                Resource.Status.LOADING -> {
                    TODO()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    finish()
                }
                Resource.Status.SUCCESS -> {
                    product = it.data!!
                    with(binding){
                        editName.setText(product.name)
                        editSellPrice.setText(product.sellPrice.toString())
                        editCost.setText(product.cost.toString())
                        editQuantity.setText(product.quantity.toString())
                        image.loadImage(this@AddProductActivity, product.image)
                    }
                }
            }
        })
    }

    private var imagePath: Uri? = null
    private fun imageFromGalleryPressed(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        imagePickLauncher.launch(intent)
    }

    private var imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            data?.data?.let{imagePath = it}
            binding.image.loadImage(this, product.image)
        }
    }

    private fun completeBtnClicked(){
        val name: String= binding.editName.text.toString()
        val sellPrice: Double = binding.editSellPrice.text.toString().toDouble()
        val cost: Double = binding.editCost.text.toString().toDouble()
        val quantity: Long = binding.editQuantity.text.toString().toLong()
        val result = if(isNewProduct) addProductViewModel.addProduct(ProductWithoutId(name, quantity, cost, sellPrice))
            else TODO()// addProductViewModel.updateProductDetails(product.id, name, quantity, cost, sellPrice)
        result.observe(this, { when(it.status) {
            Resource.Status.LOADING -> TODO()
            Resource.Status.ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            Resource.Status.SUCCESS -> {
                if (imagePath == null) finish()
                else{
                    TODO()
                }
            }
        }})
    }

    override fun finish(){
        val intent = Intent()
        if(::product.isInitialized) intent.putExtra("productId", product.id)
        intent.putExtra("product_position", productPos)
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}
