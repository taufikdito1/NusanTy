package com.example.nusanty_capstoneproject.ui.activity.main.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Camera Fragment"
    }
    val text: LiveData<String> = _text
}