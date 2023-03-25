package com.example.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.NewsData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData
    private val trigger = MutableStateFlow("")

    fun setQuery(query: String) {
        trigger.value = query
    }
    val results: Flow<List<NewsData>> = trigger.mapLatest { query ->
        repository.searchNews(query, true)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun getNews(query: String) {
        viewModelScope.launch() {
            _newsLiveData.value = repository.searchNews(query, true)
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}