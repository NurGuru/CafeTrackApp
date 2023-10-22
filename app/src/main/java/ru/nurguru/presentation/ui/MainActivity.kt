package ru.nurguru.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nurguru.databinding.MainActivityBinding
import java.lang.IllegalStateException
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for MainActivity must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdminEnter.setOnClickListener {
            Navigator.toAdminScreen(
                WeakReference(supportFragmentManager),
                AdminFragment.newInstance()
            )
        }
    }
}