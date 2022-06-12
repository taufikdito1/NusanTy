package com.example.nusanty_capstoneproject.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ArticleRepository(
    application: Application
) {
    private val articleDao: ArticleDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

   init {
    val db = NusantyDatabase.getDatabase(application)
    articleDao = db.ArticleDao()
   }

     fun insert(article: List<DetailDB>){
        executorService.execute { articleDao.inset(article) }
    }

    fun getArticle(): List<DetailDB> = articleDao.getArticle()

    fun getArticleById(title: String): LiveData<DetailDB> = articleDao.getArticleById(title)


}