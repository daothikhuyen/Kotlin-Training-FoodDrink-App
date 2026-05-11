package com.example.exerciseapplication.ui.wine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.databinding.ItemBeerBinding

class WineAdapter(
    var onDetailClick: (item: WineEntity) -> Unit,
    var toggleCollection: (item: WineEntity) -> Unit
): ListAdapter<WineEntity, WineAdapter.BeerViewHolder>(MenuDiffCallback) {

    inner class BeerViewHolder(var binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: WineEntity){
            binding.imgWine.load(item.image)
            binding.tvTitleWinery.text = item.winery
            binding.tvTitleWine.text = item.wine
            binding.btnAdd.visibility = if (item.isCollected) View.GONE else View.VISIBLE

            binding.btnAdd.setOnClickListener {
                binding.btnAdd.visibility = View.GONE
                toggleCollection.invoke(item)
            }

            binding.cardItemWine.setOnClickListener {
                onDetailClick.invoke(item)
            }
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
        val MenuDiffCallback = object : DiffUtil.ItemCallback<WineEntity>() {

            override fun areItemsTheSame(oldItem: WineEntity, newItem: WineEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WineEntity, newItem: WineEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}