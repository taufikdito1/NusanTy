package com.example.nusanty_capstoneproject.data.model.article

data class DetailResponse(
    val error : Boolean,
    val detailArticle: List<DetailArticle>,
    val message : String
)
