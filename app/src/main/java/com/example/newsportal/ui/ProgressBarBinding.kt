package com.example.newsportal.ui

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ProgressBarBinding {
    @JvmStatic
    @BindingAdapter("goneUnless")
    fun ProgressBar.loading (visible: Boolean) {
        isVisible = visible
    }
}