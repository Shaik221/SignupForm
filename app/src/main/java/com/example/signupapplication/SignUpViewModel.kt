package com.example.signupapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.signupapplication.model.User
import com.example.signupapplication.util.Event

class SignUpViewModel(application: Application):
    AndroidViewModel(application) {

    val confirmEvent: LiveData<Event<User>>
        get() = _confirmEvent
    private val _confirmEvent = MutableLiveData<Event<User>>()

   fun onClickSubmit(firstName: String, email:String, site:String) {
       _confirmEvent.value = Event(User(firstName, email, site))
   }

}