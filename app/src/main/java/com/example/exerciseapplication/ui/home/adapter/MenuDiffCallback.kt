package com.example.exerciseapplication.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exerciseapplication.model.MenuItem

class MenuDiffCallback : DiffUtil.ItemCallback<MenuItem>() {

    // Đây có phải cùng 1 item không? : true: có thể sữa nội dung
    override fun areItemsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ): Boolean {
        return oldItem == newItem
    }

    // Đây có phải cùng một nội dung không?: true không cần update
    override fun areContentsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ): Boolean {
        return oldItem == newItem
    }
}