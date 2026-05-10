package com.example.exerciseapplication.ui.food

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.di.Injection
import com.example.exerciseapplication.data.domain.entities.MenuItem
import com.example.exerciseapplication.ui.detail.DetailActivity
import com.example.exerciseapplication.utils.bottomsheet.AddItemBottomSheet
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.ui.food.adapter.FoodAdapter
import com.example.exerciseapplication.utils.AppConstants
import com.example.exerciseapplication.utils.setBorderColor
import kotlinx.coroutines.launch
import kotlin.getValue

class FoodFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels{
        Injection.provideHomeVMFactory(requireContext())
    }

    private val isFood: Boolean = true

    private val adapter by lazy {
        FoodAdapter(
            onDeleteItem = ::onDelete,
            onUpdateItem = ::onUpdate,
            onStateItem = ::onState,
            onSeeDetail = ::onSeeDetail
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvItem.adapter = adapter
        binding.rvItem.layoutManager = LinearLayoutManager(context)
        listFoodLiveData(adapter)
    }

    fun listFoodLiveData(adapter: FoodAdapter) {
        binding.tvHeader.setBorderColor(R.color.lightRed, R.color.redBorder, 6)
        binding.tvHeader.text = getString(R.string.listFood)
        binding.progressBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.food.collect { list ->
                adapter.submitList(list)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    fun onDelete(item: MenuItem) {
        viewModel.deleteFood(item)
    }

    fun onUpdate(item: Parcelable? = null) {
        AddItemBottomSheet.newInstance(isFood, item)
            .show(childFragmentManager, AppConstants.BOTTOM_SHEET_TAG)
    }

    fun onState(item: MenuItem) {
        viewModel.selectedFood(item)
    }

    fun onSeeDetail(item: MenuItem) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(AppConstants.MENU_DETAIL, item)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): FoodFragment {
            val fragment = FoodFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}