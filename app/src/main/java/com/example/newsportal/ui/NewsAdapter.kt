package com.example.newsportal.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.databinding.RvNewsBinding

class NewsAdapter(private val itemCLick: (String) -> Unit) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val news = mutableListOf<NewsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding, itemCLick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(news[position])
    }

    override fun getItemCount(): Int = news.size

    fun setNews(news: List<NewsEntity>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }
}
