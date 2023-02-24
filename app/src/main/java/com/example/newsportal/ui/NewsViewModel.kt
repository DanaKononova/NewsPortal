package com.example.newsportal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsportal.R
import com.example.newsportal.domain.models.NewsArticleData
import com.example.newsportal.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _newsLiveData = MutableLiveData<List<NewsArticleData>>()
    val newsLiveData: LiveData<List<NewsArticleData>> get() = _newsLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
        when (throwable) {
            is SocketTimeoutException -> _errorLiveData.value = R.string.socketTimeout
            else -> _errorLiveData.value = R.string.exception
        }
    }

    fun getNews() {
        _loadingLiveData.value = true
        viewModelScope.launch(handler) {
            _newsLiveData.value = repository.getNews()
            _loadingLiveData.value = false
        }
    }

    fun setToken(token: String){
        repository.setToken(token)
    }
}