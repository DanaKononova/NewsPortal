package com.example.feature.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.core.findDependencies
import com.example.feature.R
import com.example.feature.di.DaggerFeatureComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SecondViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFeatureComponent.factory().create(findDependencies()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recycler = findViewById<RecyclerView>(R.id.rvSitesList)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemClick: (String) -> Unit = {
            val address = Uri.parse(it)
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            this.startActivity(openLinkIntent)
        }
        val editText = findViewById<EditText>(R.id.editText)
        val adapter = NewsAdapter(itemClick)
        recycler.adapter = adapter

        editText.addTextChangedListener { text ->
            if (text.toString() != "") {
                viewModel.getNews(text.toString())
            } else adapter.setNews(emptyList())
        }

        viewModel.newsLiveData.observe(this) {
            adapter.setNews(it)
        }

        viewModel.setToken("273f20ec5b99445fb433eed37faf3eb5")
    }
}