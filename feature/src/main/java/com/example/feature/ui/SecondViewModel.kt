package com.example.feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.NewsData
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val trigger = MutableStateFlow("")

    fun setQuery(query: String) {
        trigger.value = query
    }
    val results: Flow<List<NewsData>> = trigger.mapLatest { query ->
        repository.searchNews(query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun setToken(token: String) {
        repository.setToken(token)
    }
}