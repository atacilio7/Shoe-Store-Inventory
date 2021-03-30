package com.udacity.shoestore.shoe.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel: ViewModel() {

    private val _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe

    private val _shoeList = ArrayList<Shoe>()
    val shoeList = MutableLiveData<List<Shoe>>()

    init {
        Timber.i("ShoeListViewModel created")
    }

    fun onAddShoe() {
        _eventAddShoe.value = true
    }

    fun onAddShoeCompleted() {
        _eventAddShoe.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeListViewModel cleared")
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.add(shoe)
        shoeList.value = _shoeList
    }

}