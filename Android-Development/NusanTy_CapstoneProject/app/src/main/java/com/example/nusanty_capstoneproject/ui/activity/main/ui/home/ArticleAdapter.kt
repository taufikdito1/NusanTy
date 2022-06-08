package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.databinding.DetailListItemBinding
import com.google.gson.Gson

class ArticleAdapter(private val a : String): RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    var gson = Gson()
    var list = gson.fromJson(a,DetailResponse::class.java)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ArticleAdapter.ListViewHolder {
        val bind =  DetailListItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ListViewHolder, position: Int) {
        val article = list.detailArticle[position]
        Glide.with(holder.bind.root).load(article.article_imgUrl).into(holder.bind.imgItemImage)
        holder.bind.tvItemTitle.text = article.article_title
        holder.bind.tvItemLocation.text =  article.article_Location
    }

    override fun getItemCount(): Int = list.detailArticle.size


    class ListViewHolder(var bind: DetailListItemBinding) : RecyclerView.ViewHolder(bind.root)
}