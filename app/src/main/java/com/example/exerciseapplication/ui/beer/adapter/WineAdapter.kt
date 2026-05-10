package com.example.exerciseapplication.ui.beer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.databinding.ItemBeerBinding

class WineAdapter(): ListAdapter<Wine, WineAdapter.BeerViewHolder>(MenuDiffCallback) {

    class BeerViewHolder(var binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Wine){
            binding.imgWine.load(item.image)
            binding.tvTitleWinery.text = item.winery
            binding.tvTitleWine.text = item.wine
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeerViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BeerViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val MenuDiffCallback = object : DiffUtil.ItemCallback<Wine>() {

            override fun areItemsTheSame(oldItem: Wine, newItem: Wine): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Wine, newItem: Wine
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}