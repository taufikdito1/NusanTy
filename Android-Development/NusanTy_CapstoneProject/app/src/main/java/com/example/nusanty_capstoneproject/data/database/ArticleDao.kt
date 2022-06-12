package com.example.nusanty_capstoneproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inset(Article : List<DetailDB>)

    @Query("SELECT * FROM article_adat")
    fun getArticle(): List<DetailDB>

    @Query( "SELECT * FROM article_adat WHERE article_title = :title")
    fun getArticleById(title: String): LiveData<DetailDB>

    @Query("DELETE FROM article_adat")
    suspend fun deleteAll()
}