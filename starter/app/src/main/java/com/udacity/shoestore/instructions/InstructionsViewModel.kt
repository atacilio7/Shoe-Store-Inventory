package com.udacity.shoestore.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class InstructionsViewModel: ViewModel() {

    private val _eventCallShoeList = MutableLiveData<Boolean>()
    val eventCallShoeList: LiveData<Boolean>
        get() = _eventCallShoeList

    init {
        Timber.i("InstructionsViewModel created")
    }

    fun onCallShoeList() {
        _eventCallShoeList.value = true
    }

    fun onCallShoeListFinished() {
        _eventCallShoeList.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("InstructionsViewModel cleared")
    }
}