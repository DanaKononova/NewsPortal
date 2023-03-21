package com.example.newsportal.di

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.newsportal.ui.MainActivity
import com.example.newsportal.ui.NewsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindLoginViewModel(viewModel: NewsViewModel): ViewModel
}