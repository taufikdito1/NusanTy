package com.example.nusanty_capstoneproject.data.model.article

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_adat")
data class DetailDB(
    val article_imgUrl : String? = null,
    val article_Description: String? = null,
    val article_Location : String? = null,
    @PrimaryKey
    val id : Int = 0,
    val article_title : String? = null
)