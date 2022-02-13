package com.example.signupapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.signupapplication.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
    }
}