package ru.nurguru.presentation.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import org.koin.android.ext.android.inject
import ru.nurguru.databinding.ProductEddFragmentBinding
import ru.nurguru.domain.CategoryUseCase
import ru.nurguru.domain.MenuUseCase
import ru.nurguru.domain.model.Product
import java.lang.ref.WeakReference




class ProductEddFragment : Fragment() {

    private lateinit var binding: ProductEddFragmentBinding
    private val menuUseCase: MenuUseCase by inject()
    private val categoryUseCase: CategoryUseCase by inject()
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private var selectedId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ProductEddFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()

        //с 49 по 59 строчки - это для spiner чтоб при создании товара в момент выбора категории
        //отображался список уже  имеющихся категории
        val suggestions = ArrayList<String>()
        val list = categoryUseCase.getCategories()
        for (i in list) {
            suggestions.add(i.categoryName)
        }

        binding.productCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            suggestions
        )

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    imageUri = data?.data
                    imageUri?.let { uri ->
                        // Запрашиваем разрешение на постоянное использование URI
                        activity?.contentResolver?.takePersistableUriPermission(
                            uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                        binding.ivAddProductImage.setImageURI(uri)
                    }
                }
            }


        with(binding) {
            btnAddProductImg.setOnClickListener {
                val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                galleryIntent.type = "image/*"
                imagePickerLauncher.launch(galleryIntent)

            }

            btnCreationReady.setOnClickListener {
                menuUseCase.addProduct(
                    Product(
                        menuUseCase.getProducts().size + 1,
                        imageUri.toString(),
                        productName.text.toString(),
                        productCategory.selectedItem.toString(),
                        productPrice.text.toString().toInt(),
                        productPrimeCost.text.toString().toInt()
                    )
                )
                Navigator.backToMenuScreen(
                    WeakReference(childFragmentManager),
                    MenuFragment.newInstance()
                )
            }

            btnChangeProduct.setOnClickListener() {
                val uri = if (imageUri != null) {
                    imageUri.toString()
                } else {
                    menuUseCase.getProductById(selectedId)?.productImg
                }

                menuUseCase.getProductById(selectedId)?.productId?.let { it1 ->
                    Product(
                        productId = it1,
                        uri.toString(),
                        productName.text.toString(),
                        productCategory.selectedItem.toString(),
                        productPrice.text.toString().toInt(),
                        productPrimeCost.text.toString().toInt()
                    )
                }?.let { it2 ->
                    menuUseCase.updateProduct(

                        product = it2,
                        id =it2.productId
                    )
                }
                Navigator.backToMenuScreen(
                    WeakReference(childFragmentManager),
                    MenuFragment.newInstance()
                )
            }

            btnDeleteProduct.setOnClickListener {
                menuUseCase.deleteProduct(menuUseCase.getProductById(selectedId)!!.productId)
                Navigator.backToMenuScreen(
                    WeakReference(childFragmentManager),
                    MenuFragment.newInstance()
                )
            }

            btnCanselCreation.setOnClickListener {
                Navigator.backToMenuScreen(
                    WeakReference(childFragmentManager),
                    MenuFragment.newInstance()
                )
            }
        }
    }

    private fun getBundle() {
        val args = this.arguments
        if (args?.getString("name") != null) {
            selectedId = args.getInt("id")
            with(binding) {
                ivAddProductImage.setImageURI((args.getString("uri"))?.toUri())
                productName.setText(args.getString("name"))
                //                productCategory.selectedItem.(args.getString("category")) не знаю как при
//                открывании карточки уже созданного товара, отображать в спинере уже выбранный параметр
                productPrice.setText(args.getString("price"))
                productPrimeCost.setText(args.getString("primeCost"))
                tvProductCreation.text = "Редактирование товара"
                btnCreationReady.visibility = View.INVISIBLE
                btnChangeProduct.visibility = View.VISIBLE
                btnDeleteProduct.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductEddFragment()
    }

}
