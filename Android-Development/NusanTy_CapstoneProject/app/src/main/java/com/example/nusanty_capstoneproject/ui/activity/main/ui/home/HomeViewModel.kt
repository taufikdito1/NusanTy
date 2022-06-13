package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.app.Application
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nusanty_capstoneproject.data.database.ArticleRepository
import com.example.nusanty_capstoneproject.data.database.NusantyDatabase
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.data.networking.ApiConfig
import com.example.nusanty_capstoneproject.ui.activity.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selamat Datang"
    }
    val text: LiveData<String> = _text

    private val _article = MutableLiveData<DetailResponse>()
    val article: LiveData<DetailResponse> = _article


    private val _toastText = MutableLiveData<String>()
    val toasText: LiveData<String> = _toastText

    fun getArticle(){
        ApiConfig().getApiService().getArticle()
            .enqueue(object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful){
                        _article.value = response.body()
                        _toastText.value = response.body()?.message!!
                        Log.e("response", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    _toastText.value = t.localizedMessage
                }

            })
    }
}
