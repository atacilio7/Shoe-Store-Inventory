package com.udacity.shoestore.shoe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.shoe.list.ShoeListViewModel
import timber.log.Timber

class ShoeDetailViewModel: ViewModel() {

    private val _eventSaveAndGoBackToShoeList = MutableLiveData<Boolean>()
    val eventSaveAndGoBackToShoeList: LiveData<Boolean>
        get() = _eventSaveAndGoBackToShoeList

    private val _eventCancelAndGoBackToShoeList = MutableLiveData<Boolean>()
    val eventCancelAndGoBackToShoeList: LiveData<Boolean>
        get() = _eventCancelAndGoBackToShoeList

    init {
        Timber.i("ShoeDetailViewModel created")
    }

    fun onSaveAndGoBackToShoeList() {
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