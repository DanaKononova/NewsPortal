package com.example.newsportal

import android.app.Application
import com.example.core.HasDependencies
import com.example.newsportal.di.AppComponent
import com.example.newsportal.di.DaggerAppComponent

class HiltApplication : Application(), HasDependencies {
    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }

    override val dependencies: Any
        get() = appComponent
}