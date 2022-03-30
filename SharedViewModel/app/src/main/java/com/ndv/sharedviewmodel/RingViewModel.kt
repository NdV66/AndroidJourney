package com.ndv.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RingViewModel : ViewModel() {
    private var _name = MutableLiveData("")
    private var _metal = MutableLiveData("")

    val name: LiveData<String> = _name
    val metal: LiveData<String> = _metal

    fun setName(value: String) {
        _name.value = value
    }

    fun setMetal(value: String) {
        _metal.value = value
    }
}