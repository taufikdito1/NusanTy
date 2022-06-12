package com.example.nusanty_capstoneproject.data.model.article

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article_adat")
data class DetailDB(
    @PrimaryKey
    val id : Long = 0,
    val article_title : String? = null,
    val article_imgUrl : String? = null,
    val article_Description: String? = null,
    val article_Location : String? = null
)