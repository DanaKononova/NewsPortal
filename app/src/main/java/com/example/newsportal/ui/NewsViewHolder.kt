package com.example.newsportal.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsportal.R
import com.example.newsportal.databinding.RvNewsBinding
import com.example.newsportal.domain.models.NewsArticleData

class NewsViewHolder(private val itemNewsBinding: RvNewsBinding) : RecyclerView.ViewHolder(itemNewsBinding.root) {
    fun onBind(item: NewsArticleData) {
      //  val title = itemView.findViewById<TextView>(R.id.title)
        itemNewsBinding.title.text = item.title

       // val newsUrl = itemView.findViewById<TextView>(R.id.site)
        itemNewsBinding.site.text = item.newsUrl

       // val description = itemView.findViewById<TextView>(R.id.description)
        itemNewsBinding.description.text = item.description

       // val image = itemView.findViewById<ImageView>(R.id.image)

        Glide
            .with(itemView)
            .load(item.urlToImage)
            .into(itemNewsBinding.image)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.newsUrl)
            itemNewsBinding.site.context.startActivity(intent)
        }
    }
}