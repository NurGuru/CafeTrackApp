package ru.nurguru.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import ru.nurguru.databinding.MenuFragmentBinding
import ru.nurguru.domain.MenuUseCase
import ru.nurguru.presentation.MenuAdapter
import java.lang.ref.WeakReference


class MenuFragment : Fragment() {
    private lateinit var binding: MenuFragmentBinding

    private val menuUseCase: MenuUseCase by inject()

    val menuAdapter = MenuAdapter(ArrayList()) {

        val productEddFragment = ProductEddFragment()

            val bundle = Bundle().apply {
                putInt("id", it.productId)
                putString("uri", it.productImg)
                putString("name", it.productName)
                putString("category", it.category)
                putString("price", it.price.toString())
                putString("primeCost", it.primeCost.toString())
            }
            productEddFragment.arguments = bundle


        Navigator.toProductEddScreen(
            WeakReference(childFragmentManager),
            productEddFragment
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MenuFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
            btnAddProduct.setOnClickListener {
                Navigator.toProductEddScreen(
                    WeakReference(childFragmentManager),
                    ProductEddFragment.newInstance()
                )
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = menuAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        menuAdapter.updateAdapter(menuUseCase.getProducts())
    }

    companion object {

        fun newInstance() = MenuFragment()
    }
}
