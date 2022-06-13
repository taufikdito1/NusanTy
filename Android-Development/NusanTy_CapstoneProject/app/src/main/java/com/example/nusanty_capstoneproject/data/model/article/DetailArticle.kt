package com.example.nusanty_capstoneproject.data.model.article

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailArticle(

    @field:SerializedName("photoUrl")
    val photoUrl: List<String>? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null

) :Parcelable
