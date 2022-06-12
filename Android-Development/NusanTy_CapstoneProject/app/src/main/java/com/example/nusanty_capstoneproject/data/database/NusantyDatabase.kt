package com.example.nusanty_capstoneproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [DetailDB::class],
    version = 1,
    exportSchema = false
)

abstract class NusantyDatabase: RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao


    companion object{
        @Volatile
        private var INSTANSE: NusantyDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NusantyDatabase{
            return INSTANSE ?: synchronized(this){
                INSTANSE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NusantyDatabase::class.java,"Nusanty_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANSE = it }
            }
        }
    }
}