package com.example.exerciseapplication.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.databinding.ItemMenuBinding
import com.example.exerciseapplication.model.MenuItem

class MenuViewAdapter(
    var onDeleteItem: (MenuItem) -> Unit,
    var onUpdateItem: (MenuItem) -> Unit
    ) : ListAdapter<MenuItem, MenuViewAdapter.MenuViewHolder>(MenuDiffCallback) {
    inner class MenuViewHolder(var binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: MenuItem) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text = "%,.000f đ".format(item.price.toDouble())

            binding.ibDelete.setOnClickListener { onDeleteItem.invoke(item) }
            binding.llItemView.setOnClickListener { onUpdateItem.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, postion: Int) {
        val item = getItem(postion)
        holder.bind(item)
    }

    companion object {
        val MenuDiffCallback = object : DiffUtil.ItemCallback<MenuItem>() {

            // Đây có phải cùng 1 id không? :  true = cùng 1 đối tượng trong danh sách
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem.id == newItem.id
            }

            // Đây có phải cùng một nội dung không?: true không cần update ui
            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}