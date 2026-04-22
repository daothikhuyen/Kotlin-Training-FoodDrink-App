package com.example.exerciseapplication.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.databinding.ItemMenuBinding
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem

class MenuViewAdapter(
    var onDeleteItem: (Any) -> Unit,
    var onUpdateItem: (item: Parcelable?) -> Unit,
    var onStateItem: (Any) -> Unit
) : ListAdapter<Any, MenuViewAdapter.MenuViewHolder>(MenuDiffCallback) {

    inner class MenuViewHolder(var binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Any) {
            val itemId: Int
            val itemName: String
            val itemPrice: String

            when (item) {
                is MenuFoodItem -> {
                    itemId = item.id
                    itemName = item.name
                    itemPrice = item.price.toString()
                }

                is MenuDrinkItem -> {
                    itemId = item.id
                    itemName = item.name
                    itemPrice = item.price.toString()
                }

                else -> return
            }

            binding.tvTitle.text = itemName
            binding.tvPrice.text = "%,.000f đ".format(itemPrice.toDouble())

            binding.ibDelete.setOnClickListener { onDeleteItem.invoke(item) }
            binding.llItemView.setOnClickListener { onStateItem.invoke(item) }
            binding.ibUpdate.setOnClickListener { onUpdateItem.invoke(item) }
        }

        fun setStateItem(isSelect: Boolean) {
            if (isSelect) {
                binding.tvTitle.setTextColor(Color.RED)
            } else {
                binding.tvTitle.setTextColor(Color.BLACK)
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
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads.contains("SELECT_CHANGE")) {
                val item = getItem(position)
                val isSelected = if (item is MenuFoodItem) item.isSelect else (item as MenuDrinkItem) .isSelect
                holder.setStateItem(isSelected)
            }
        }
    }

    companion object {
        val MenuDiffCallback = object : DiffUtil.ItemCallback<Any>() {

            // Đây có phải cùng 1 id không? :  true = cùng 1 đối tượng trong danh sách
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                if(oldItem is MenuFoodItem && newItem is MenuFoodItem){
                    return oldItem.id == newItem.id
                }
                if(oldItem is MenuDrinkItem && newItem is MenuDrinkItem){
                    return oldItem.id == newItem.id
                }
                return false
            }

            // Đây có phải cùng một nội dung không?: true không cần update ui
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                if (oldItem is MenuFoodItem && newItem is MenuFoodItem) {
                    return oldItem == newItem
                }
                if(oldItem is MenuDrinkItem && newItem is MenuDrinkItem){
                    return oldItem == newItem
                }
                return false
            }

            override fun getChangePayload(oldItem: Any, newItem: Any): Any? {

                if (oldItem is MenuFoodItem && newItem is MenuFoodItem) {
                    if (oldItem.isSelect != newItem.isSelect) {
                        return "SELECT_CHANGE"
                    }
                }
                if(oldItem is MenuDrinkItem && newItem is MenuDrinkItem) {
                    if (oldItem.isSelect != newItem.isSelect) {
                        return "SELECT_CHANGE"
                    }
                }
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }
}