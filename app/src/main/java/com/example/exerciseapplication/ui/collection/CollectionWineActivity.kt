package com.example.exerciseapplication.ui.collection

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.databinding.ActivityCollectionWineBinding
import com.example.exerciseapplication.di.Injection
import com.example.exerciseapplication.ui.collection.adapter.CollectionWineAdapter
import com.example.exerciseapplication.ui.detailwine.DetailWineActivity
import com.example.exerciseapplication.ui.wine.WineViewModel
import com.example.exerciseapplication.utils.AppConstants
import kotlin.getValue

class CollectionWineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectionWineBinding

    private val wineViewModel: CollectionViewModel by viewModels {
        Injection.provideWineCollectionVMFactory(this)
    }


    private val adapter by lazy {
        CollectionWineAdapter(
            toggleCollection = :: toggleCollection,
            onDetailClick = ::onDetailClick
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCollectionWineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setUpAdapterCollectionWine()
    }

    private fun setUpAdapterCollectionWine(){
        binding.rvCollections.adapter = adapter
        binding.rvCollections.layoutManager = LinearLayoutManager(this)
        wineViewModel.collections.asLiveData().observe(this) { list ->
            adapter.submitList(list)
        }
    }

    fun toggleCollection(item: WineEntity) {
        wineViewModel.toggleCollection(item);
    }

    fun onDetailClick(item: WineEntity) {
        val intent = Intent(this, DetailWineActivity::class.java)
        intent.putExtra(AppConstants.WINE_DETAIL, item)
        startActivity(intent)
    }
}