package com.example.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.NewsData
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    fun getNews(query: String) {
        viewModelScope.launch() {
            _newsLiveData.value = repository.getNews(query, true)
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}