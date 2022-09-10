package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.utils.getErrorMessageByException

abstract class BaseActivity<B: ViewBinding, VM: ViewModel>(
    val bindingFactory: (LayoutInflater) -> B
): AppCompatActivity() {
    protected lateinit var binding: B
    protected abstract val viewModel: VM

    override fun onCreate(savedStateInstance: Bundle?) {
        super.onCreate(savedStateInstance)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initView()
        observeData()
    }

    abstract fun initView()

    abstract fun observeData()

    open fun showError(isErrorEnabled: Boolean, exception: Exception) {
        if(isErrorEnabled) {
            Toast.makeText(this, getErrorMessageByException(exception), Toast.LENGTH_SHORT).show()
        }
    }

    fun enableHomeAsBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}