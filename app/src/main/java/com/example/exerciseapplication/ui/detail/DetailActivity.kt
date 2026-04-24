package com.example.exerciseapplication.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.ActivityDetailFoodDrinkBinding
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem
import com.example.exerciseapplication.utils.AppConstants

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

    private fun loadData() {

        foodDetail = intent.getParcelableExtra(AppConstants.FOOD_DETAIL)
        drinkDetail = intent.getParcelableExtra(AppConstants.DRINK_DETAIL)

        when {
            foodDetail != null -> {
                binding.tvName.text = foodDetail!!.name
                binding.tvPrice.text = binding.root.context.getString(R.string.priceFormatDetail, foodDetail!!.price)
                binding.tvType.text = binding.root.context.getString(R.string.type, foodDetail!!.type)
                binding.tvDescription.text = foodDetail!!.description
            }

            drinkDetail != null -> {
                binding.tvName.text = drinkDetail!!.name
                binding.tvPrice.text = binding.root.context.getString(R.string.priceFormatDetail, drinkDetail!!.price)
                binding.tvType.text = binding.root.context.getString(R.string.type, drinkDetail!!.type)
                binding.tvDescription.text = drinkDetail!!.description
            }
        }

    }
}