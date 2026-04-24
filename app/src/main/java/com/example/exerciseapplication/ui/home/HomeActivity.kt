package com.example.exerciseapplication.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.ActivityHomeBinding
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem
import com.example.exerciseapplication.ui.detail.DetailActivity
import com.example.exerciseapplication.ui.home.adapter.HomeAdapter
import com.example.exerciseapplication.utils.AppConstants
import com.example.exerciseapplication.utils.bottomsheet.AddItemBottomSheet
import kotlin.getValue

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tvFood -> {
                    binding.viewPage.currentItem = TAG_FOOD
                    true
                }

                R.id.tvDrink -> {
                    binding.viewPage.currentItem = TAG_DRINK
                    true
                }

                else -> false
            }
        }
    }

    private fun setupViewPager() {
        binding.viewPage.adapter = HomeAdapter(this@HomeActivity)

        // Đồng bộ viewpager2 với bottom navigation
        binding.viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavView.menu[position].isChecked = true
            }
        })

        binding.ibAdd.setOnClickListener {
            val isFood = binding.viewPage.currentItem == TAG_FOOD
            AddItemBottomSheet.newInstance(isFood).show(supportFragmentManager, AppConstants.BOTTOM_SHEET_TAG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Huỷ activity")
    }

    // biến dùng chung cho tất cả class
    companion object {
        const val TAG_FOOD = 0
        const val TAG_DRINK = 1
    }

}