package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.databinding.DetailListItemBinding
import com.google.gson.Gson

class ArticleAdapter(private val a : String): RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    var gson = Gson()
    var list = gson.fromJson(a,DetailResponse::class.java)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ListViewHolder {
        val bind =  DetailListItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = list.detailArticle[position]
        Glide.with(holder.bind.root).load(article.article_imgUrl?.get(0)).placeholder(R.drawable.ic_baseline_image_24).error(
            R.drawable.ic_baseline_broken_image_24).into(holder.bind.imgItemImage)
        holder.bind.tvItemTitle.text = article.article_title
        holder.bind.tvItemLocation.text =  article.article_Location

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(article)
        }
    }

    override fun getItemCount(): Int = list.detailArticle.size

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailArticle)
    }

    class ListViewHolder(var bind: DetailListItemBinding) : RecyclerView.ViewHolder(bind.root)
}