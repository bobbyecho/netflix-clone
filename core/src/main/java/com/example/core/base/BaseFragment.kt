package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.utils.getErrorMessageByException

abstract class BaseFragment<B: ViewBinding, VM: ViewModel>(
    val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> B
): Fragment() {
    protected lateinit var binding: B
    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater, container, false)
        return binding.root
    }

    abstract fun initView()

    open fun observeData() {
        TODO("Not yet implemented")
    }

    open fun showError(isErrorEnabled: Boolean, exception: Exception) {
        if(isErrorEnabled) {
            Toast.makeText(requireContext(), requireContext().getErrorMessageByException(exception), Toast.LENGTH_SHORT).show()
        }
    }
}