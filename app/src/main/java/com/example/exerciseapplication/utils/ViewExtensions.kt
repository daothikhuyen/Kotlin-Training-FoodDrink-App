package com.example.exerciseapplication.utils

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat

fun View.setBorderColor(
    backgroundColor: Int? = null,
    borderColor: Int? = null,
    borderSize: Int = 4
) {
    val drawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 20f

        backgroundColor?.let {
            setColor(ContextCompat.getColor(context, it))
        }

        borderColor?.let {
            setStroke(borderSize, ContextCompat.getColor(context, it))
        }
    }

    this.background = drawable
}