package com.example.newsportal

import android.app.Application
import com.example.newsportal.di.AppComponent
import com.example.newsportal.di.DaggerAppComponent

class HiltApplication : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }
}