package com.example.signupapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_NAME = "name"
private const val ARG_EMAIL = "email"

class ConfirmationFragment : Fragment() {
    private var name: String? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, email: String) =
            ConfirmationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_EMAIL, email)
                }
            }
    }
}