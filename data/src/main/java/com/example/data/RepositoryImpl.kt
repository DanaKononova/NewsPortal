package com.example.data

import com.example.data.dataBase.NewsEntity
import com.example.data.mappers.NewsArticleMapper
import com.example.data.mappers.NewsEntityMapper
import com.example.domain.Repository
import com.example.domain.models.NewsData
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsArticleMapper,
    private val netService: UserDataSource,
    private val dataBaseSource: DataBaseSource,
    private val entityMapper: NewsEntityMapper
) : Repository {
    override fun getResponseToDataBase(): Completable {
        return service.getNews(netService.getUserToken(), "Belarus").flatMapCompletable {
            val newsList = it.article?.map { article -> mapper(article) } ?: emptyList()
            dataBaseSource.insertAll(newsList)
            Completable.complete()
        }
    }

    override fun getNews(): Observable<List<NewsData>> {
        val newsList = dataBaseSource.getAll().map { list ->
            list.map { entityMapper(it) }
        }
        return newsList
    }

    override fun searchNews(query: String): Single<List<NewsData>> {
        return service.getNews(netService.getUserToken(), query).map {
            val newsList = (it.article ?: listOf()).map { article -> mapper(article) }
            newsList.map { article -> entityMapper(article) }
        }
    }

    override fun setToken(token: String) {
        netService.setUserToken(token)
    }
}