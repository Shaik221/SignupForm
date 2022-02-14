package com.example.signupapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.signupapplication.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {

    private lateinit var binding: FragmentConfirmationBinding

    private val sharedViewModel: SignUpSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationBinding.inflate(inflater).apply {
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel. getRegisteredUser()?.let {
            binding.nameId.text = it.firstName
            binding.emailId.text = it.email
            binding.websiteId.text = it.webSite

            binding.nameTitle.text = resources.getString(R.string.name_header, it.firstName)
        }
        sharedViewModel.changeNameVisibility()

        binding.signBtn.setOnClickListener {
            Toast.makeText(requireContext(),"Sign in Clicked!", Toast.LENGTH_SHORT).show()
        }

    }
}