package com.example.newsportal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsportal.R
import com.example.domain.Repository
import com.example.domain.models.NewsData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _noInternetLiveData = MutableLiveData<Boolean>()
    val noInternetLiveData: LiveData<Boolean> get() = _noInternetLiveData

//    init {
//        viewModelScope.launch() {
//            repository.getNews("Apple", true).collect{
//                _newsLiveData.value = it
//            }
//        }

    fun getNews(query: String) {
        _loadingLiveData.value = true
        _noInternetLiveData.value = false
        viewModelScope.launch() {
             repository.getNews(query).collect{
                 _newsLiveData.value = it
                 _loadingLiveData.value = false
             }
        }
    }

    fun isDataBaseEmpty(): Boolean {
        var isEmpty = false
        viewModelScope.launch {
            isEmpty = _newsLiveData.value?.isEmpty() ?: true
        }
        return isEmpty
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}