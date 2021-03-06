package com.example.nusanty_capstoneproject.ui.activity.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.databinding.ActivityDetailArticleBinding
import com.example.nusanty_capstoneproject.ui.activity.main.MainActivity

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailArticleBinding
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var imageList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getParcelableExtra<DetailArticle>(DETAIL_ARTICLE) as DetailArticle

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.vpArticle
        val detailArticleViewModel =
            ViewModelProvider(this)[DetailArticleViewModel::class.java]

        imageList = ArrayList()
        imageList = imageList + data.photoUrl!!

        viewPagerAdapter = ViewPagerAdapter(this,imageList)
        viewPager.adapter = viewPagerAdapter

            binding.tvArticleName.text = data.title

            binding.tvLocation.text = data.location

            binding.tvDetailArticle.text = data.description

    }

    override fun onBackPressed() {
        val intent = Intent(this@DetailArticleActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val DETAIL_ARTICLE = "DETAIL ARTICLE INI DAN ITU"
    }
}