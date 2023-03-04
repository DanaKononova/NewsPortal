package com.example.newsportal.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LoadingBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 60.0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 15f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var alpha = 0f
        paint.color = Color.rgb(187, 134, 252)
        canvas.drawArc(
            width / 2 - radius,
            height / 2 - radius,
            width / 2 + radius,
            height / 2 + radius,
            30f,
            300f,
            false,
            paint
        )
        this.animate().rotationBy(alpha).rotation(alpha + 7200).setDuration(18000).start()
        alpha += 7200
    }
}