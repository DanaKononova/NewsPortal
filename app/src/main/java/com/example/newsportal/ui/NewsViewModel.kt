package com.example.newsportal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsportal.R
import com.example.domain.Repository
import com.example.domain.models.NewsData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

    private val composite = CompositeDisposable()

    init {
        refreshData()
        getNews()
    }

    private fun refreshData() {
        val disposable = repository.getResponseToDataBase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        composite.add(disposable)
    }

    fun getNews() {
        _loadingLiveData.value = true
        _noInternetLiveData.value = false
        val disposable = repository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _newsLiveData.value = it
                _loadingLiveData.value = false
            },
                {
                    _errorLiveData.value = it.hashCode()
                    _loadingLiveData.value = false
                })
        composite.add(disposable)
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

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}