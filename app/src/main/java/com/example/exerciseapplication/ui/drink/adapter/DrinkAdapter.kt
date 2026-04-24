package com.example.exerciseapplication.ui.drink.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.ItemMenuBinding
import com.example.exerciseapplication.model.MenuDrinkItem

class DrinkAdapter(
    var onDeleteItem: (MenuDrinkItem) -> Unit,
    var onUpdateItem: (item: MenuDrinkItem) -> Unit,
    var onStateItem: (MenuDrinkItem) -> Unit,
    var onSeeDetail: (item: MenuDrinkItem) -> Unit
) : ListAdapter<MenuDrinkItem, DrinkAdapter.MenuViewHolder>(MenuDiffCallback) {

    inner class MenuViewHolder(var binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuDrinkItem) {

            binding.tvTitle.text = item.name
            binding.tvPrice.text = binding.root.context.getString(R.string.priceFormat, item.price)

            binding.ibDelete.setOnClickListener { onDeleteItem.invoke(item) }
            binding.llItemView.setOnClickListener { onSeeDetail.invoke(item) }
            binding.ibUpdate.setOnClickListener { onUpdateItem.invoke(item) }
            binding.ibFavorite.setOnClickListener { onStateItem.invoke(item) }
            setStateItem(item.isFavorite)
        }

        fun setStateItem(isFavorite: Boolean) {
            if (isFavorite) {
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_24dp_red)
            } else {
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_24dp_grey)
            }
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

    override fun onBindViewHolder(
        holder: MenuViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            holder.bind(getItem(position))
        } else {
            val payload = payloads[0]
            if (payload == true) {
                holder.setStateItem(getItem(position).isFavorite)
            } else {
                holder.bind(getItem(position))
            }
        }
    }

    companion object {
        val MenuDiffCallback = object : DiffUtil.ItemCallback<MenuDrinkItem>() {

            // Đây có phải cùng 1 id không? :  true = cùng 1 đối tượng trong danh sách
            override fun areItemsTheSame(oldItem: MenuDrinkItem, newItem: MenuDrinkItem): Boolean {
                return oldItem.id == newItem.id
            }

            // Đây có phải cùng một nội dung không?: true không cần update ui
            override fun areContentsTheSame(
                oldItem: MenuDrinkItem, newItem: MenuDrinkItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: MenuDrinkItem, newItem: MenuDrinkItem): Boolean {
                return oldItem.isFavorite != newItem.isFavorite
            }
        }
    }
}