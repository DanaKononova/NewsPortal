package com.example.newsportal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsportal.R
import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.domain.Repository
import com.example.newsportal.domain.models.NewsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
        when (throwable) {
            is SocketTimeoutException -> {
                _errorLiveData.value = R.string.socketTimeout
            }
            else -> _errorLiveData.value = R.string.exception
        }
    }

    fun getNews(isConnected: Boolean) {
        _loadingLiveData.value = true
        viewModelScope.launch(handler) {
            _newsLiveData.value = repository.getNews(isConnected)
            _loadingLiveData.value = false
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