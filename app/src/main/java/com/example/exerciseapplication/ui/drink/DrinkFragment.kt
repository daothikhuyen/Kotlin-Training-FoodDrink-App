package com.example.exerciseapplication.ui.drink

import android.content.Intent
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
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.ui.detail.DetailActivity
import com.example.exerciseapplication.utils.bottomsheet.AddItemBottomSheet
import com.example.exerciseapplication.ui.drink.adapter.DrinkAdapter
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.utils.setBorderColor
import kotlin.getValue

class DrinkFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel:  HomeViewModel by activityViewModels()
    private var isFood: Boolean = false

    private val adapter by lazy {
        DrinkAdapter(
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

        istDrinkLiveData(adapter)
    }

    fun onDelete(item: MenuDrinkItem) {
        viewModel.deleteDrinkItem(item)
    }

    fun onUpdate(item: Parcelable? = null) {
        AddItemBottomSheet.newInstance(isFood, item)
            .show(childFragmentManager, "AddBottomSheet")
    }

    fun onState(item: MenuDrinkItem) {
        viewModel.selectedDrink(item)
    }

    fun onSeeDetail(item: MenuDrinkItem) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("DrinkDetail", item)
        startActivity(intent)
    }

    fun istDrinkLiveData(adapter: DrinkAdapter) {
        binding.tvHeader.setBorderColor(R.color.lightGreen, R.color.greenMain)
        binding.tvHeader.text = getString(R.string.listDrink)

        // observe: lắng nghe sự thay đổi của dữ liệu
        viewModel.drink.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): DrinkFragment {
            val fragment = DrinkFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}