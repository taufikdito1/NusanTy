package com.example.nusanty_capstoneproject.data.model.article

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailArticle(
    @field:SerializedName("article_ID")
    val article_ID: String? = null,
    @field:SerializedName("article_title")
    val article_title : String? = null,
    @field:SerializedName("article_imgUrl")
    val article_imgUrl : List<String>? = null,
    @field:SerializedName("article_Description")
    val article_Description: String? = null,
    @field:SerializedName("article_Location")
    val article_Location : String? = null
):Parcelable
