package com.example.signupapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.signupapplication.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignupBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(SignUpViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.firstNameText.text.toString()
        val email = binding.emailAddressText.text.toString()
        val webSite = binding.websiteText.text.toString()

        binding.submitBtn.setOnClickListener {
            viewModel.onClickSubmit(name, email, webSite)
        }

        startObserving()
    }

    fun startObserving() {
        viewModel.confirmEvent.observe(this) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, ConfirmationFragment())
                .commit()
        }
    }
}