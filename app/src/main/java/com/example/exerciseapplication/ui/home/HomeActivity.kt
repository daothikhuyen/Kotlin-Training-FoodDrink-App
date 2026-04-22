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
import com.example.exerciseapplication.ui.home.fragment.bottomsheet.AddItemBottomSheet
import com.example.exerciseapplication.ui.home.HomeViewModel
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
        onPriceChange()
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
                val isFood = position == TAG_FOOD
                viewModel.onTabChanged(isFood)
            }
        })

        binding.ibAdd.setOnClickListener {
            val isFood = binding.viewPage.currentItem == TAG_FOOD
            AddItemBottomSheet.newInstance(isFood).show(supportFragmentManager, "AddBottomSheet")
        }

        binding.btnDetail.setOnClickListener { onSeeDetail() }
    }

    private fun onSeeDetail() {
        val isFood = binding.viewPage.currentItem == TAG_FOOD
        val item = viewModel.item.value

        if (item == null) {
            Toast.makeText(this, "Vui lòng chọn món", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, DetailActivity::class.java)

        when (item) {
            is MenuFoodItem -> {
                intent.putExtra("FoodDetail", item)
            }

            is MenuDrinkItem -> {
                intent.putExtra("DrinkDetail", item)
            }
        }

        startActivity(intent)
    }

    private fun onPriceChange() {
        viewModel.item.observe(this) { newItem ->
            val newPrice = when (newItem) {
                is MenuFoodItem -> "%,.0f đ".format(newItem.price.toDouble())
                is MenuDrinkItem -> "%,.0f đ".format(newItem.price.toDouble())
                else -> "0đ"
            }
            binding.tvPriceProduct.text = newPrice
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