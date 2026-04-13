package com.example.exerciseapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.exerciseapplication.databinding.ActivityHomeBinding
import com.example.exerciseapplication.ui.adapter.HomeAdapter
import com.example.exerciseapplication.ui.drink.DrinkFragment
import com.example.exerciseapplication.ui.fastfood.FoodFragment
import androidx.core.view.get

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupBottomNavigation()
        setupButtons()
    }

    private fun setupViewPager() {
        binding.viewPage.adapter = HomeAdapter(this)

        // Đồng bộ viewpager2 với bottom navigation
        binding.viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavView.menu[position].isChecked = true
            }
        })
    }

    private fun setupBottomNavigation() {
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tvFood -> {
                    binding.viewPage.currentItem = 0
                    true
                }
                R.id.tvDrink -> {
                    binding.viewPage.currentItem = 1
                    true
                }
                else -> false
            }
        }
    }

    private fun setupButtons() {
        binding.btnNext.setOnClickListener {
            navigateCurrentFragment(isNext = true)
        }

        binding.btnPrevious.setOnClickListener {
            navigateCurrentFragment(isNext = false)
        }
    }

    private fun navigateCurrentFragment(isNext: Boolean) {
        when (val currentFragment = getCurrentFragment()) {
            is DrinkFragment -> {
                if (isNext) currentFragment.nextItem() else currentFragment.previousItem()
            }
            is FoodFragment -> {
                if (isNext) currentFragment.nextItem() else currentFragment.previousItem()
            }
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentByTag("f${binding.viewPage.currentItem}")
    }
}