package com.example.exerciseapplication.ui.detailwine

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.databinding.ActivityDetailWineBinding
import com.example.exerciseapplication.utils.AppConstants

class DetailWineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailWineBinding
    private var itemWine: WineEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_wine)
        binding = ActivityDetailWineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        loadData()
    }

    private fun loadData(){
        itemWine = intent.getParcelableExtra(AppConstants.WINE_DETAIL)
        binding.tvWinery.text = itemWine!!.winery
        binding.tvWine.text = getString(R.string.wine, itemWine!!.wine)
        binding.tvLocation.text = itemWine!!.location
        binding.imageView.load(itemWine!!.image)
    }
}