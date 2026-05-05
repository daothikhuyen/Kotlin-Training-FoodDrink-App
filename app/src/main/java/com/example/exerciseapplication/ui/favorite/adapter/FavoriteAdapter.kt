package com.example.exerciseapplication.ui.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseapplication.ui.favoriteitem.FavoriteItem

class FavoriteAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteItem.newInstance(true)
            1 -> FavoriteItem.newInstance(false)
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getItemCount(): Int = 2
}