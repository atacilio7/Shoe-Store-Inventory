package com.udacity.shoestore.models

import android.os.Parcelable
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable

@BindingAdapter("android:text")
fun EditText.doubleToString(value: Double) {
    setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun EditText.stringToDouble(): Double? {
    val result = text.toString()
    if (result.isEmpty()) {
        return Double.NaN
    }
    return result.toDouble()
}