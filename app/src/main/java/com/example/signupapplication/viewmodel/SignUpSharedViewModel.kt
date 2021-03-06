package com.example.signupapplication.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.signupapplication.model.User
import com.example.signupapplication.util.RegexValidation

class SignUpSharedViewModel: ViewModel() {

    private val _registeredUser = MutableLiveData<User>()

    fun setUserDetails(user: User) {
        _registeredUser.value = user
    }

    fun getRegisteredUser() = _registeredUser.value

    private val _isVisible: MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val isVisible
        get() = Transformations.map(_isVisible) {
            when (it) {
                true -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }

    fun changeNameVisibility() {
        getRegisteredUser()?.let {
            _isVisible.value = it.firstName.isNotEmpty()
        }
    }

    fun isRegexMatch(input: String) =
        (input.isNotEmpty() && !input.matches(RegexValidation().emailPattern))

    fun isRequired(input: String) = (input.trim().isEmpty())
}