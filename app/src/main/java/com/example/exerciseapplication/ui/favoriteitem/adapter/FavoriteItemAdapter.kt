package com.example.exerciseapplication.ui.favoriteitem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.ItemFavoriteBinding
import com.example.exerciseapplication.domain.entities.MenuItem

class FavoriteItemAdapter : ListAdapter<MenuItem, FavoriteItemAdapter.MenuViewHolder>(MenuDiffCallback)  {

    class MenuViewHolder(var binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text = binding.root.context.getString(R.string.priceFormat, item.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
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
                return oldItem == newItem // return false go payloads
            }
        }
    }
}