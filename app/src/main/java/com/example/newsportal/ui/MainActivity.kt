package com.example.newsportal.ui

import android.content.Intent
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.R
import com.example.newsportal.databinding.ActivityMainBinding
import com.example.newsportal.network.NetworkManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<NewsViewModel>()
    private val networkManager = NetworkManager()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val recycler = findViewById<RecyclerView>(R.id.rvSitesList)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemClick: (String) -> Unit = {
            val address = Uri.parse(it)
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            this.startActivity(openLinkIntent)
        }
        val adapter = NewsAdapter(itemClick)
        recycler.adapter = adapter

        viewModel.newsLiveData.observe(this) {
            adapter.setNews(it)
        }

        if (networkManager.isNetworkAvailable(this)) {
            viewModel.getNews(true)
        } else {
            if (viewModel.isDataBaseEmpty()) {
                Toast.makeText(this, getString(R.string.emptyDB), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getNews(false)
                Toast.makeText(this, getString(R.string.noConnection), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        }

        viewModel.setToken("273f20ec5b99445fb433eed37faf3eb5")
    }
}