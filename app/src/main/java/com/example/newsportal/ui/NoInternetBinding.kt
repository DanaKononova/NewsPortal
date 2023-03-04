package com.example.newsportal.ui

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

object NoInternetBinding {
    @JvmStatic
    @BindingAdapter("goneUnless")
    fun LottieAnimationView.setVisibility(visible: Boolean?) {
        if (visible != null) {
            isVisible = visible
        }
    }
}