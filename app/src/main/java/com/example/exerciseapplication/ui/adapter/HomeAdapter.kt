package com.example.exerciseapplication.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseapplication.ui.drink.DrinkFragment
import com.example.exerciseapplication.ui.fastfood.FoodFragment

class HomeAdapter (activity: AppCompatActivity)
    : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoodFragment()
            else -> DrinkFragment()
        }
    }
}