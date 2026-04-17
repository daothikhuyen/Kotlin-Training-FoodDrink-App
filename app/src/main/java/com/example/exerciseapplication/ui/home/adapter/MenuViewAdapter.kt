package com.example.exerciseapplication.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.databinding.ItemMenuBinding
import com.example.exerciseapplication.model.MenuItem

class MenuViewAdapter : RecyclerView.Adapter<MenuViewAdapter.MenuViewHolder>() {

    private var list: List<MenuItem> = emptyList()
    var onDeleteItem: ((MenuItem) -> Unit)? = null

    var onUpdateItem: ((MenuItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<MenuItem>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(var binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: MenuItem) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text =  "%,.000f đ".format(item.price.toDouble())

            binding.ibDelete.setOnClickListener { onDeleteItem?.invoke(item) }
            binding.llItemView.setOnClickListener { onUpdateItem?.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, postion: Int) {
        val item = list[postion]
        holder.bind(item)
    }


    override fun getItemCount(): Int = list.size
}