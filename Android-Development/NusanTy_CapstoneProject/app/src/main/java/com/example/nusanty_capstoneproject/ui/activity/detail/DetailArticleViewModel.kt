package com.example.nusanty_capstoneproject.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DetailArticleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selain simbol kebudayaan, menurut buku Pakaian Adat Tradisional Daerah Propinsi Daerah Khusus Ibukota Jakarta (1995) yang ditulis oleh Abdurachman, dkk, pakaian adat Indonesia juga merupakan salah satu unsur kebudayaan daerah yang memiliki pesan atau nilai di dalamnya. Pesan atau nilai ini bisa dilihat melalui berbagai ragam hias yang ada pada baju adat masing-masing daerah.  \n" +
                "Pesan serta nilai ini ternyata juga memiliki keterkaitan dengan beberapa aspek lain seperti aspek ekonomi, sosial, politik dan juga keamanan."
    }
    val text: LiveData<String> = _text


}

