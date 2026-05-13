package com.example.exerciseapplication.ui.favoriteitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.databinding.FragmentFavoriteItemBinding
import com.example.exerciseapplication.ui.favoriteitem.adapter.FavoriteItemAdapter
import com.example.exerciseapplication.ui.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import kotlin.getValue

class FavoriteItem : Fragment() {

    private var _binding: FragmentFavoriteItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel:  HomeViewModel by activityViewModel()

    private val adapter by lazy {
        FavoriteItemAdapter()
    }

    private var isFood: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isFood = arguments?.getBoolean(TYPE) ?: false
        binding.rvItem.adapter = adapter
        binding.rvItem.layoutManager = LinearLayoutManager(context)

        if (isFood) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.foodFavorite.collect {
                    binding.progressBar.visibility = View.VISIBLE
                    adapter.submitList(it)
                    binding.progressBar.visibility = View.GONE
                }
            }
        } else {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.drinkFavorite.collect {
                    binding.progressBar.visibility = View.VISIBLE
                    adapter.submitList(it)
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        private const val TYPE = "isFavorite"
        fun newInstance(isFood: Boolean): FavoriteItem {
            val fragment = FavoriteItem()
            val bundle = Bundle()
            bundle.putBoolean(TYPE, isFood)
            fragment.arguments = bundle
            return fragment
        }
    }


}