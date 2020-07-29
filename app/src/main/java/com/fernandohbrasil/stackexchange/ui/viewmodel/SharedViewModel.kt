package com.fernandohbrasil.stackexchange.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SharedViewModel @Inject constructor() : ViewModel() {

    private var _appBar: MutableLiveData<AppBar> = MutableLiveData()

    val appBar: LiveData<AppBar>
        get() = _appBar

    fun setValuesAppBar(title: String = "", hasUpButton: Boolean) {
        _appBar.postValue(AppBar(title, hasUpButton))
    }
}

data class AppBar(val title: String, val hasUpButton: Boolean)