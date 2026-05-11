package com.example.exerciseapplication.ui.collection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.databinding.ItemBeerBinding

class CollectionWineAdapter(
    var toggleCollection: (item: WineEntity) -> Unit,
    var onDetailClick: (item: WineEntity) -> Unit
) : ListAdapter<WineEntity, CollectionWineAdapter.BeerViewHolder>(MenuDiffCallback) {

    inner class BeerViewHolder(var binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: WineEntity){
            binding.imgWine.load(item.image)
            binding.tvTitleWinery.text = item.winery
            binding.tvTitleWine.text = item.wine
            binding.btnAdd.setImageResource(R.drawable.ic_minus_28dp_red)

            binding.btnAdd.setOnClickListener {
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