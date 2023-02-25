package com.example.newsportal.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.databinding.RvNewsBinding
import com.example.newsportal.domain.models.NewsData

class NewsAdapter(private val itemCLick: (String) -> Unit) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val news = mutableListOf<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding, itemCLick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(news[position])
    }

    override fun getItemCount(): Int = news.size

    fun setNews(news: List<NewsData>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }
}
