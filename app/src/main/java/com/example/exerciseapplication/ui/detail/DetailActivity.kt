package com.example.exerciseapplication.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseapplication.databinding.ActivityDetailFoodDrinkBinding
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodDrinkBinding

    private var foodDetail: MenuFoodItem? = null
    private var drinkDetail: MenuDrinkItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFoodDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {

        foodDetail = intent.getParcelableExtra("FoodDetail")
        drinkDetail = intent.getParcelableExtra("DrinkDetail")

        when {
            foodDetail != null -> {
                binding.tvName.text = foodDetail!!.name
                binding.tvPrice.text = "Giá: %,.0f đ".format(foodDetail!!.price.toDouble())
                binding.tvType.text = "Phân loại: ${foodDetail!!.type}"
                binding.tvDescription.text = foodDetail!!.description
            }

            drinkDetail != null -> {
                binding.tvName.text = drinkDetail!!.name
                binding.tvPrice.text = "Giá: %,.0f đ".format(drinkDetail!!.price.toDouble())
                binding.tvType.text = "Phân loại: ${drinkDetail!!.type}"
                binding.tvDescription.text = drinkDetail!!.description
            }
        }

    }
}