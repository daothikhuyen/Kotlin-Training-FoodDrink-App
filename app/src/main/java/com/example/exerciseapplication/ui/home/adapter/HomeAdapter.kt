package com.example.exerciseapplication.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseapplication.ui.home.fragment.drink.DrinkFragment
import com.example.exerciseapplication.ui.home.fragment.food.FoodFragment

class HomeAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FoodFragment.newInstance()
        } else {
            DrinkFragment.newInstance()
        }
    }
}