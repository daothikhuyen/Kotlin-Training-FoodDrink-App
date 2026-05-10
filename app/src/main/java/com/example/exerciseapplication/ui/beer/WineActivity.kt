package com.example.exerciseapplication.ui.beer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.databinding.ActivityBeerBinding
import com.example.exerciseapplication.di.Injection
import com.example.exerciseapplication.ui.beer.adapter.WineAdapter
import kotlin.getValue

class WineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeerBinding
    private val wineViewModel: WineViewModel by viewModels {
        Injection.provideWineVMFactory(this)
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

    private fun setupRecyclerView(){
        val adapter = WineAdapter()
        binding.rvWine.adapter = adapter
        binding.rvWine.layoutManager = LinearLayoutManager(this)
        wineViewModel.wines.asLiveData().observe(this){ list ->
            adapter.submitList(list)
        }
    }
}