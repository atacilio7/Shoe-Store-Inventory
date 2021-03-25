package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel: ViewModel() {

    private val _eventCallWelcome = MutableLiveData<Boolean>()
    val eventCallWelcome: LiveData<Boolean>
        get() = _eventCallWelcome

    init {
        Timber.i("LoginViewModel created")
    }

    fun onCallWelcome() {
        _eventCallWelcome.value = true
    }

    fun onCallWelcomeFinished() {
        _eventCallWelcome.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("LoginViewModel cleared")
    }
}