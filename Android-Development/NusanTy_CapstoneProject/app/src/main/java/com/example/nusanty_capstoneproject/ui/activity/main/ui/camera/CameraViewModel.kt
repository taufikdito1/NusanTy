package com.example.nusanty_capstoneproject.ui.activity.main.ui.camera

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nusanty_capstoneproject.data.database.ArticleRepository
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.data.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Camera Fragment test"
    }
    val text: LiveData<String> = _text

    private val _Carticle = MutableLiveData<List<DetailArticle>>()
    val Carticle: LiveData<List<DetailArticle>> = _Carticle


    private val _toastText = MutableLiveData<String>()
    val toasText: LiveData<String> = _toastText

}