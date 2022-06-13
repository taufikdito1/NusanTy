package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.app.Activity
import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.data.database.ArticleRepository
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.databinding.DetailListItemBinding
import com.google.gson.Gson
import java.lang.StringBuilder

class ArticleAdapter(private val list : List<DetailArticle>): RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ListViewHolder {
        val bind =  DetailListItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = list[position]


        val img = article.photoUrl?.toList()
        Glide.with(holder.bind.root).load(img?.get(0)).placeholder(R.drawable.ic_baseline_image_24).error(
            R.drawable.ic_baseline_broken_image_24).into(holder.bind.imgItemImage)
        holder.bind.tvItemTitle.text = article.title
        holder.bind.tvItemLocation.text =  StringBuilder("Pakaian adat ${article.location}")

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(article)
        }
    }

    override fun getItemCount(): Int = list.size

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailArticle)
    }

    class ListViewHolder(var bind: DetailListItemBinding) : RecyclerView.ViewHolder(bind.root)
}