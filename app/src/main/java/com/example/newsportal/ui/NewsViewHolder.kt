package com.example.newsportal.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.databinding.RvNewsBinding
import com.example.newsportal.domain.models.NewsData

class NewsViewHolder(
    private val itemNewsBinding: RvNewsBinding,
    private val itemCLick: (String) -> Unit
) : RecyclerView.ViewHolder(itemNewsBinding.root) {
    fun onBind(item: NewsData) {
        itemNewsBinding.title.text = item.title
        itemNewsBinding.site.text = item.newsUrl
        itemNewsBinding.description.text = item.description

        Glide
            .with(itemNewsBinding.root.context)
            .load(item.urlToImage)
            .into(itemNewsBinding.image)

        itemNewsBinding.root.setOnClickListener {
            itemCLick.invoke(item.newsUrl)
        }
    }
}