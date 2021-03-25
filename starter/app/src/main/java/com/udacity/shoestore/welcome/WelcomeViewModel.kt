package com.udacity.shoestore.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class WelcomeViewModel: ViewModel() {

    private val _eventCallInstructions = MutableLiveData<Boolean>()
    val eventCallInstructions: LiveData<Boolean>
        get() = _eventCallInstructions

    init {
        Timber.i("WelcomeViewModel created")
    }

    fun onCallInstructions() {
        _eventCallInstructions.value = true
    }

    fun onCallInstructionsFinished() {
        _eventCallInstructions.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("WelcomeViewModel cleared")
    }

}