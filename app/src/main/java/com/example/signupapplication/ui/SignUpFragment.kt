package com.example.signupapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.signupapplication.R
import com.example.signupapplication.databinding.FragmentSignupBinding
import com.example.signupapplication.model.User
import com.example.signupapplication.util.RegexValidation
import com.example.signupapplication.viewmodel.SignUpSharedViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding

    private val sharedViewModel: SignUpSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        binding.submitBtn.setOnClickListener {
            sharedViewModel.setUserDetails(
                User(
                    binding.firstNameText.text.toString(),
                    binding.emailAddressText.text.toString(),
                    binding.passwordText.text.toString(),
                    binding.websiteText.text.toString()
                )
            )
            // checking validations
            if (isValidate()) {
                loadConfirmFragment()
            }
        }
    }

    private fun isValidate(): Boolean = validateEmail() && validatePassword()

    fun loadConfirmFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, ConfirmationFragment()
            ).commit()
    }

    private fun setupListeners() {
        binding.emailAddressText.addTextChangedListener(TextFieldValidation(binding.emailAddressText))
        binding.passwordText.addTextChangedListener(TextFieldValidation(binding.passwordText))
    }

    private fun validatePassword(): Boolean {
        if (binding.passwordText.text.toString().trim().isEmpty()) {
            binding.passwordId.error = resources.getString(R.string.password_required)
            binding.passwordText.requestFocus()
            return false
        } else {
            binding.passwordId.isErrorEnabled = false
        }
        return true
    }


    private fun validateEmail(): Boolean {
        val email = binding.emailAddressText.text.toString()
        if (email.trim().isEmpty()) {
            binding.emailId.error = resources.getString(R.string.email_required)
            binding.emailAddressText.requestFocus()
            return false
        } else if(email.isNotEmpty() &&
                !email.matches(RegexValidation().emailPattern)) {
            binding.emailId.error = resources.getString(R.string.please_enter_valid_email)
            binding.emailAddressText.requestFocus()
            return false
        } else {
            binding.emailId.isErrorEnabled = false
        }
        return true
    }

    /**
     * applying text watcher on each text field
     */
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
          when(view.id) {
              R.id.email_address_text -> {
                  validateEmail()
              }
              R.id.password_text -> {
                  validatePassword()
              }
          }
        }

    }
}