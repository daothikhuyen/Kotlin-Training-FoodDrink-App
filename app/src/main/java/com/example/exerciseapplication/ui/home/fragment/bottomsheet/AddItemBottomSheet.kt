package com.example.exerciseapplication.ui.home.fragment.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.exerciseapplication.databinding.LayoutAddItemBinding
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddItemBottomSheet : BottomSheetDialogFragment() {
    private var _binding: LayoutAddItemBinding? = null
    private val binding get() = _binding!!
    private var isFood: Boolean = true
    private var item: Any? = null

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutAddItemBinding.inflate(inflater, container, false)
        arguments?.let {
            isFood = it.getBoolean(ARG_TYPE, true)
            item = it.getParcelable(ARG_ITEM)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.text = if (item == null) "Thêm" else "Cập nhật"
        onLoad()

        binding.btnAdd.setOnClickListener { onSubmit() }
    }

    private fun onLoad() {
        when (val it = item) {
            is MenuFoodItem -> {
                binding.edtName.setText(it.name)
                binding.edtPrice.setText(it.price.toString())
            }

            is MenuDrinkItem -> {
                binding.edtName.setText(it.name)
                binding.edtPrice.setText(it.price.toString())
            }

            else -> return
        }
    }

    private fun onSubmit() {
        val name = binding.edtName.text.toString()
        val priceText = binding.edtPrice.text.toString()
        if (name.isEmpty() || priceText.isEmpty()) {
            Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toIntOrNull() ?: 0
        if (item == null) {
            viewModel.addItem(isFood, name, price, "đồ ăn")
        } else {
            when (val it = item) {
                is MenuFoodItem -> {
                    val newItem = it.copy(name = name, price = price)
                    viewModel.updateItem(true, newItem)
                }

                is MenuDrinkItem -> {
                    val newItem = it.copy(name = name, price = price)
                    viewModel.updateItem(false, newItem)
                }
            }
        }
        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TYPE = "isFood"
        private const val ARG_ITEM = "item"

        fun newInstance(type: Boolean, item: Parcelable? = null): AddItemBottomSheet {
            val fragment = AddItemBottomSheet()
            val bundle = Bundle()
            bundle.putBoolean(ARG_TYPE, type)
            bundle.putParcelable(ARG_ITEM, item)
            fragment.arguments = bundle
            return fragment
        }
    }
}