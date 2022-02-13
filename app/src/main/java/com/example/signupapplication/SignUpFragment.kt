package com.example.signupapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.signupapplication.databinding.FragmentSignupBinding
import com.example.signupapplication.model.User

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

        binding.submitBtn.setOnClickListener {
            sharedViewModel.setUserDetails(
                User(
                    binding.firstNameText.text.toString(),
                    binding.emailAddressText.text.toString(),
                    binding.websiteText.text.toString()
                )
            )
            loadConfirmFragment()
        }
    }

    fun loadConfirmFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, ConfirmationFragment()
            ).commit()
    }
}