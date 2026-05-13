package com.example.exerciseapplication.ui.wine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.databinding.ActivityBeerBinding
import com.example.exerciseapplication.ui.collection.CollectionWineActivity
import com.example.exerciseapplication.ui.detailwine.DetailWineActivity
import com.example.exerciseapplication.ui.wine.adapter.WineAdapter
import com.example.exerciseapplication.utils.AppConstants
import kotlin.getValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class WineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeerBinding
    private  val wineViewModel: WineViewModel by viewModel ()

    private val allAdapter by lazy {
        WineAdapter(
            toggleCollection = :: toggleCollection,
            onDetailClick = ::onDetailClick
        )
    }

    private val filterAdapter by lazy {
        WineAdapter(
            toggleCollection = :: toggleCollection,
            onDetailClick = ::onDetailClick
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        val allAdapter = allAdapter
        val filterAdapter = filterAdapter

        binding.rvWine.adapter = allAdapter
        binding.rvWine.layoutManager = LinearLayoutManager(this)

        binding.rvFilterWine.adapter = filterAdapter
        binding.rvFilterWine.layoutManager = LinearLayoutManager(this)

        wineViewModel.wines.asLiveData().observe(this) { list ->
            allAdapter.submitList(list)
        }

        wineViewModel.filteredWines.asLiveData().observe(this) { list ->
            filterAdapter.submitList(list)

            binding.tvEmpty.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE

            binding.rvFilterWine.visibility =
                if (!list.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.edtSearch.addTextChangedListener {
            wineViewModel.onSearchTextChange(it.toString())
        }

        binding.btnThreeDot.setOnClickListener {
            showMenu()
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


    fun showMenu() {
        val popupMenu = PopupMenu(this, binding.btnThreeDot)
        popupMenu.inflate(R.menu.wine_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_wine_favorite -> {
                    val intent = Intent(this, CollectionWineActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}