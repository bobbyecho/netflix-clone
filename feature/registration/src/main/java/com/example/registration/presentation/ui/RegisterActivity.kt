package com.example.registration.presentation.ui

import android.content.Intent
import androidx.core.view.isVisible
import com.example.core.base.BaseActivity
import com.example.core.exception.FieldErrorException
import com.example.registration.constants.RegisterFieldConstants
import com.example.registration.databinding.ActivityRegisterBinding
import com.example.shared.router.ActivityRouter
import com.example.shared.utils.DateUtils.showDatePickerDialog
import com.example.shared.utils.GenderUtils
import com.example.shared.utils.ext.subscribe
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(ActivityRegisterBinding::inflate) {

    private val router : ActivityRouter by inject()

    override val viewModel: RegisterViewModel by inject()

    override fun initView() {
        binding.etBirthdate.setOnClickListener {
            showDatePickerDialog {
                binding.etBirthdate.setText(it)
            }
        }
        binding.tvGender.apply {
            setAdapter(GenderUtils.createGenderListAdapter(this@RegisterActivity))
        }
        binding.btnRegister.setOnClickListener {
            viewModel.registerUser(
                email = binding.etEmail.text?.trim().toString(),
                password = binding.etPassword.text?.trim().toString(),
                username = binding.etUsername.text?.trim().toString(),
                birthdate = binding.etBirthdate.text?.trim().toString(),
                gender = binding.tvGender.text?.trim().toString()
            )
        }
    }

    override fun observeData() {
        viewModel.registerResult.observe(this) { registerResult ->
            resetField()
            registerResult.subscribe(doOnLoading = {
                showLoading(true)
            }, doOnSuccess = {
                showLoading(false)
                navigateToHome()
            }, doOnError = {
                showLoading(false)
                if (registerResult.exception is FieldErrorException) {
                    handleFieldError(registerResult.exception as FieldErrorException)
                } else {
                    showError(true, registerResult.exception as Exception)
                }
            })
        }
    }
    private fun showLoading(isShowLoading: Boolean) {
        binding.pbLoading.isVisible = isShowLoading
    }

    private fun handleFieldError(exception: FieldErrorException) {
        exception.let {
            it.errorFields.forEach { errorField ->
                if (errorField.first == RegisterFieldConstants.EMAIL_FIELD) {
                    binding.etEmail.error = getString(errorField.second)
                }
                if (errorField.first == RegisterFieldConstants.BIRTHDATE_FIELD) {
                    binding.etBirthdate.error = getString(errorField.second)
                }
                if (errorField.first == RegisterFieldConstants.USERNAME_FIELD) {
                    binding.etUsername.error = getString(errorField.second)
                }
                if (errorField.first == RegisterFieldConstants.GENDER_FIELD) {
                    binding.tvGender.error = getString(errorField.second)
                }
                if (errorField.first == RegisterFieldConstants.PASSWORD_FIELD) {
                    binding.tilPassword.endIconMode = TextInputLayout.END_ICON_NONE
                    binding.etPassword.error = getString(errorField.second)
                }
            }
        }
    }

    private fun resetField() {
        binding.tilEmail.isErrorEnabled = false
        binding.tilPassword.isErrorEnabled = false
        binding.tilGender.isErrorEnabled = false
        binding.tilBirthdate.isErrorEnabled = false
        binding.tilUsername.isErrorEnabled = false
        binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }
    private fun navigateToHome() {
        startActivity(router.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}