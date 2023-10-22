package ru.nurguru.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nurguru.databinding.AdminFragmentBinding
import java.lang.IllegalStateException
import java.lang.ref.WeakReference


class AdminFragment : Fragment() {
    private var _binding: AdminFragmentBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for MainActivity must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AdminFragmentBinding.inflate(inflater)

        binding.btnWorkWithGuests.setOnClickListener {
            Navigator.toGuestsScreen(WeakReference(childFragmentManager),
            GuestFragment.newInstance())
        }

        binding.btnWorkWithMenu.setOnClickListener {
            Navigator.toMenuScreen(WeakReference(childFragmentManager),
            MenuFragment.newInstance())
        }

        binding.btnWorkWithCategory.setOnClickListener {
            Navigator.toCategoryScreen(WeakReference(childFragmentManager),
                CategoryFragment.newInstance())
        }

        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = AdminFragment()
    }
}
