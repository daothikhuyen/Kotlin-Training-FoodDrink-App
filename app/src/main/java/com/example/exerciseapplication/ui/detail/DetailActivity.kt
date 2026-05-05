package com.example.exerciseapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.ActivityDetailFoodDrinkBinding
import com.example.exerciseapplication.domain.entities.MenuItem
import com.example.exerciseapplication.utils.AppConstants

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodDrinkBinding

    private var menuDetail: MenuItem? = null

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

        menuDetail = intent.getParcelableExtra(AppConstants.MENU_DETAIL)

        val typeString = if (menuDetail!!.type == AppConstants.DRINK) R.string.nuocUong else R.string.doAn

        binding.tvName.text = menuDetail!!.name
        binding.tvPrice.text = binding.root.context.getString(R.string.priceFormatDetail, menuDetail!!.price)
        binding.tvType.text = binding.root.context.getString(R.string.type, getString(typeString))
        binding.tvDescription.text = menuDetail!!.description
    }
}