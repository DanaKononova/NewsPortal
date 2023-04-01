package com.example.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.NewsData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val composite = CompositeDisposable()

    fun getNews(query: String) {
        _loadingLiveData.value = true
        val disposable = repository.searchNews(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value -> _newsLiveData.value = value }

        composite.add(disposable)
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}