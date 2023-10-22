package ru.nurguru.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import ru.nurguru.R
import ru.nurguru.databinding.CategoryFragmentBinding
import ru.nurguru.databinding.MenuFragmentBinding
import ru.nurguru.domain.CategoryUseCase
import ru.nurguru.domain.MenuUseCase
import ru.nurguru.presentation.CategoryAdapter
import ru.nurguru.presentation.MenuAdapter
import java.lang.ref.WeakReference


class CategoryFragment : Fragment() {
    private lateinit var binding: CategoryFragmentBinding

    private val categoryUseCase: CategoryUseCase by inject()

    val categoryAdapter = CategoryAdapter(ArrayList()) {

        val categoryAddFragment = CategoryAddFragment()

        val bundle = Bundle().apply {
            putInt("id", it.categoryId)
            putString("uri", it.categoryImg)
            putString("name", it.categoryName)
        }
        categoryAddFragment.arguments = bundle


        Navigator.toCategoryAddScreen(
            WeakReference(childFragmentManager),
            categoryAddFragment
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = CategoryFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
            btnAddCategory.setOnClickListener {
                Navigator.toCategoryAddScreen(
                    WeakReference(childFragmentManager),
                    CategoryAddFragment.newInstance()
                )
            }
            val screenWidth = resources.displayMetrics.widthPixels
            val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width) // замените на ширину вашего элемента списка
            val spanCount = screenWidth / itemWidth // вычисляем количество элементов, помещающихся в ширину экрана

            val layoutManager = GridLayoutManager(requireContext(), spanCount)
            binding.categoryRecyclerView.layoutManager = layoutManager
            categoryRecyclerView.adapter = categoryAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        categoryAdapter.updateAdapter(categoryUseCase.getCategories())
    }

    companion object {
        fun newInstance() = CategoryFragment()
    }
}