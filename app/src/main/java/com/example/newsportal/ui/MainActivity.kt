package com.example.newsportal.ui

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.R
import com.example.newsportal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val recycler = findViewById<RecyclerView>(R.id.rvSitesList)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        viewModel.newsLiveData.observe(this) {
           // progressBar.isVisible = false
            val adapter = NewsAdapter(it)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getNews()

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        }

//        viewModel.loadingLiveData.observe(this) {
//            progressBar.isVisible = true
//        }

        viewModel.setToken("273f20ec5b99445fb433eed37faf3eb5")
    }
}