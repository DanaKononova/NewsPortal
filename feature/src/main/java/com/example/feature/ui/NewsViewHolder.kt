package com.example.feature.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.NewsData
import com.example.feature.databinding.RvSecondNewsBinding

class NewsViewHolder(
    private val itemNewsBinding: RvSecondNewsBinding,
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