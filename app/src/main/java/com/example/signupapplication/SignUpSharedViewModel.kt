package com.example.signupapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signupapplication.model.User

class SignUpSharedViewModel: ViewModel() {

    private val _registeredUser = MutableLiveData<User>()

    fun setUserDetails(user: User) {
        _registeredUser.value = user
    }
    fun getRegisteredUser() = _registeredUser.value

}