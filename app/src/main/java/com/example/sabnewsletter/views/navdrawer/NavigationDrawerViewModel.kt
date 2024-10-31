package com.example.sabnewsletter.views.navdrawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationDrawerViewModel:ViewModel() {

    private val _showBottomSheet=MutableLiveData<Boolean>()
    val showBottomSheet:LiveData<Boolean>
        get() = _showBottomSheet

    init {
        _showBottomSheet.value=false
    }

    fun hideBottomSheet(){
        _showBottomSheet.value=false
    }

    fun showBottomSheet(){
        _showBottomSheet.value = true
    }
}