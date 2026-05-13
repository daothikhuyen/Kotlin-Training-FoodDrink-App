package com.example.exerciseapplication.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.exerciseapplication.databinding.FragmentFavoriteBinding
import com.example.exerciseapplication.ui.favorite.adapter.FavoriteAdapter
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.utils.AppConstants
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import kotlin.getValue

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoriteAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = AppConstants.FOOD
                1 -> tab.text = AppConstants.DRINK
            }
        }.attach()
    }

    companion object {

        fun newInstance(): FavoriteFragment {
            val fragment = FavoriteFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}