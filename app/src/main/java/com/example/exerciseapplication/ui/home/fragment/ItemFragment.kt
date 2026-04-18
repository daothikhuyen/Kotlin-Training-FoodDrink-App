package com.example.exerciseapplication.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.model.MenuItem
import com.example.exerciseapplication.ui.home.adapter.MenuViewAdapter
import com.example.exerciseapplication.ui.home.viewmodel.HomeViewModel
import com.example.exerciseapplication.ui.home.fragment.AddItemBottomSheet
import com.example.exerciseapplication.utils.setBorderColor

class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels() // sửa
    private var isFood: Boolean = true

    private val adapter: MenuViewAdapter by lazy {
        MenuViewAdapter(
            onDeleteItem = { item ->
                viewModel.deleteItem(isFood, item)
            },
            onUpdateItem = { item ->
                AddItemBottomSheet.newInstance(isFood, item)
                    .show(childFragmentManager, "AddBottomSheet")
            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        isFood = arguments?.getBoolean(ARG_TYPE) ?: false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvItem.adapter = adapter
        binding.rvItem.layoutManager = LinearLayoutManager(context)

        if (isFood) listFoodLiveData(adapter) else istDrinkLiveData(adapter)
    }

    fun listFoodLiveData(adapter: MenuViewAdapter) {
        binding.tvHeader.setBorderColor(R.color.lightRed, R.color.redBorder, 6)
        binding.tvHeader.text = getString(R.string.listFood)

        viewModel.food.observe(viewLifecycleOwner) { list ->
            if(list.isNotEmpty()){ adapter.submitList(list)}
        }
    }

    fun onDelete(item: MenuItem) {
        viewModel.deleteItem(isFood, item)
    }

    fun onUpdate(item: MenuItem) {
        AddItemBottomSheet.newInstance(isFood, item)
            .show(childFragmentManager, "AddBottomSheet")
    }

    fun istDrinkLiveData(adapter: MenuViewAdapter) {
        binding.tvHeader.setBorderColor(R.color.lightGreen, R.color.greenMain)
        binding.tvHeader.text = getString(R.string.listDrink)

        // observe: lắng nghe sự thay đổi của dữ liệu
        viewModel.drink.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.submitList(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TYPE = "isFood"

        fun newInstance(type: Boolean): ItemFragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putBoolean(ARG_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

}