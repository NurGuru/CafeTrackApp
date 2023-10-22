package ru.nurguru.presentation.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import org.koin.android.ext.android.inject
import ru.nurguru.R
import ru.nurguru.databinding.CategoryAddFragmentBinding
import ru.nurguru.databinding.ProductEddFragmentBinding
import ru.nurguru.domain.CategoryUseCase
import ru.nurguru.domain.MenuUseCase
import ru.nurguru.domain.model.Category
import ru.nurguru.domain.model.Product
import java.lang.ref.WeakReference


class CategoryAddFragment : Fragment() {

    private lateinit var binding: CategoryAddFragmentBinding
    private val categoryUseCase: CategoryUseCase by inject()
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private var selectedId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = CategoryAddFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()

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
                        binding.ivAddCategoryImage.setImageURI(uri)
                    }
                }
            }


        with(binding) {
            btnAddCategoryImg.setOnClickListener {
                val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                galleryIntent.type = "image/*"
                imagePickerLauncher.launch(galleryIntent)

            }

            btnCategoryCreationReady.setOnClickListener {
                categoryUseCase.addCategory(
                    Category(
                        categoryUseCase.getCategories().size + 1,
                        imageUri.toString(),
                        categoryName.text.toString(),
                    )
                )
                Navigator.backToCategoryScreen(
                    WeakReference(childFragmentManager),
                    CategoryFragment.newInstance()
                )
            }

            btnChangeCategory.setOnClickListener() {
                val uri = if (imageUri != null) {
                    imageUri.toString()
                } else {
                    categoryUseCase.getCategoryById(selectedId)?.categoryImg
                }

                categoryUseCase.getCategoryById(selectedId)?.categoryId?.let { it1 ->
                    Category(
                        categoryId =  it1,
                        uri.toString(),
                        categoryName.text.toString()

                    )
                }?.let { it2 ->
                    categoryUseCase.updateCategory(

                        category = it2,
                        id =it2.categoryId
                    )
                }
                Navigator.backToCategoryScreen(
                    WeakReference(childFragmentManager),
                    CategoryFragment.newInstance()
                )
            }

            btnDeleteCategory.setOnClickListener {
                categoryUseCase.deleteCategory(categoryUseCase.getCategoryById(selectedId)!!.categoryId)
                Navigator.backToCategoryScreen(
                    WeakReference(childFragmentManager),
                    CategoryFragment.newInstance()
                )
            }

            btnCanselCategoryCreation.setOnClickListener {
                Navigator.backToCategoryScreen(
                    WeakReference(childFragmentManager),
                    CategoryFragment.newInstance()
                )
            }
        }
    }

    private fun getBundle() {
        val args = this.arguments
        if (args?.getString("name") != null) {
            selectedId = args.getInt("id")
            with(binding) {
                ivAddCategoryImage.setImageURI((args.getString("uri"))?.toUri())
                categoryName.setText(args.getString("name"))
                tvCategoryCreation.text = "Редактирование категории"
                btnCanselCategoryCreation.visibility = View.INVISIBLE
                btnChangeCategory.visibility = View.VISIBLE
                btnDeleteCategory.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryAddFragment()
    }
}