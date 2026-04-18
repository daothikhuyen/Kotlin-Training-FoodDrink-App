package com.example.exerciseapplication.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exerciseapplication.model.MenuItem

class MenuDiffCallback : DiffUtil.ItemCallback<MenuItem>() {

    // Đây có phải cùng 1 id không? :  true = cùng 1 đối tượng trong danh sách
    override fun areItemsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    // Đây có phải cùng một nội dung không?: true không cần update ui
    override fun areContentsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ): Boolean {
        return oldItem == newItem
    }
}