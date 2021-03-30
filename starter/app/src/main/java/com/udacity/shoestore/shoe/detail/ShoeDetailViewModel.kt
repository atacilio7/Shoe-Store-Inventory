package com.udacity.shoestore.shoe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel: ViewModel() {

    private val _eventSaveAndGoBackToShoeList = MutableLiveData<Boolean>()
    val eventSaveAndGoBackToShoeList: LiveData<Boolean>
        get() = _eventSaveAndGoBackToShoeList

    private val _eventCancelAndGoBackToShoeList = MutableLiveData<Boolean>()
    val eventCancelAndGoBackToShoeList: LiveData<Boolean>
        get() = _eventCancelAndGoBackToShoeList

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    init {
        Timber.i("ShoeDetailViewModel created")
    }

    fun onSaveAndGoBackToShoeList(shoe: Shoe) {
        _shoe.value = shoe
        _eventSaveAndGoBackToShoeList.value = true
    }

    fun onSaveAndGoBackToShoeListCompleted() {
        _eventSaveAndGoBackToShoeList.value = false
    }

    fun onCancelAndGoBackToShoeList() {
        _eventCancelAndGoBackToShoeList.value = true
    }

    fun onCancelAndGoBackToShoeListCompleted() {
        _eventCancelAndGoBackToShoeList.value = false
    }
}