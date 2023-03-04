package com.example.newsportal.ui

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object LoadingBarBinding {
    @JvmStatic
    @BindingAdapter("goneUnless")
    fun LoadingBarView.setVisibility(visible: Boolean?) {
        if (visible != null) {
            isVisible = visible
        }
    }
}