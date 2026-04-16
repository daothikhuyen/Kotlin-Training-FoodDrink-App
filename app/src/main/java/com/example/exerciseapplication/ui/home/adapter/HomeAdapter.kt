package com.example.exerciseapplication.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseapplication.ui.home.HomeActivity
import com.example.exerciseapplication.ui.home.fragment.ItemFragment

class HomeAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            HomeActivity.TAG_FOOD -> ItemFragment.newInstance(HomeActivity.TAG_FOOD)
            HomeActivity.TAG_DRINK -> ItemFragment.newInstance(HomeActivity.TAG_DRINK)
            else -> ItemFragment.newInstance(HomeActivity.TAG_FOOD)
        }
    }
}