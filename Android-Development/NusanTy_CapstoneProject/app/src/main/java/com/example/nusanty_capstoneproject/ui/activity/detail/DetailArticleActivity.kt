package com.example.nusanty_capstoneproject.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailArticleBinding
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.vpArticle
        val detailArticleViewModel =
            ViewModelProvider(this)[DetailArticleViewModel::class.java]

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.user1
        imageList = imageList + R.drawable.user2
        imageList = imageList + R.drawable.user3
        imageList = imageList + R.drawable.user4
        imageList = imageList + R.drawable.user5

        viewPagerAdapter = ViewPagerAdapter(this,imageList)
        viewPager.adapter = viewPagerAdapter

        detailArticleViewModel.text.observe(this){
            binding.tvDetailArticle.text = it
        }

    }
}