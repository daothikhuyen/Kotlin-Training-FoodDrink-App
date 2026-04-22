package com.example.exerciseapplication.ui.home.fragment.food

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.ui.home.adapter.MenuViewAdapter
import com.example.exerciseapplication.ui.home.fragment.bottomsheet.AddItemBottomSheet
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.utils.setBorderColor
import kotlin.getValue

class FoodFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    private val isFood : Boolean = true

    private val adapter by lazy {
        MenuViewAdapter(
            onDeleteItem = ::onDelete,
            onUpdateItem = ::onUpdate,
            onStateItem = ::onState
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

    fun listFoodLiveData(adapter: MenuViewAdapter) {
        binding.tvHeader.setBorderColor(R.color.lightRed, R.color.redBorder, 6)
        binding.tvHeader.text = getString(R.string.listFood)

        viewModel.food.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    fun onDelete(item: Any) {
        viewModel.deleteItem(isFood, item)
    }

    fun onUpdate(item: Parcelable? = null) {
        AddItemBottomSheet.newInstance(isFood, item)
            .show(childFragmentManager, "AddBottomSheet")
    }

    fun onState(item: Any){
        viewModel.selectOnlyOne(isFood, item)
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