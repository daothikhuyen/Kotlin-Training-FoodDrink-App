package com.example.exerciseapplication.utils

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat

fun View.setBorderColor(
    backgroundColor: Int? = null,
    borderColor: Int? = null,
    borderSize: Int = 4
) {
    val drawable = background as? GradientDrawable ?: return

    backgroundColor?.let { drawable.setColor(ContextCompat.getColor(context, it)) }
    borderColor?.let { drawable.setStroke(borderSize, ContextCompat.getColor(context, it)) }
}