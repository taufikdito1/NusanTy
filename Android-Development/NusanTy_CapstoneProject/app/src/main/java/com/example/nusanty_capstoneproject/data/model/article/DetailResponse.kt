package com.example.nusanty_capstoneproject.data.model.article

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("articles")
    val articles: List<DetailArticle>,

    @field:SerializedName("status")
    val status: Int? = null
)
