package com.example.exerciseapplication.utils.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.LayoutAddItemBinding
import com.example.exerciseapplication.domain.entities.MenuItem
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.utils.AppConstants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddItemBottomSheet : BottomSheetDialogFragment() {
    private var _binding: LayoutAddItemBinding? = null
    private val binding get() = _binding!!
    private var isFood: Boolean = true
    private var item: MenuItem? = null

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
        binding.btnAdd.text = if (item == null) getString(R.string.add) else getString(R.string.update)
        onLoad()

        binding.btnAdd.setOnClickListener { onSubmit() }
    }

    private fun onLoad() {
        binding.edtName.setText(item?.name)
        binding.edtPrice.setText(item?.price?.toString() ?: "")
        binding.edtDescription.setText(item?.description)
    }

    private fun onSubmit() {
        val name = binding.edtName.text.toString()
        val priceText = binding.edtPrice.text.toString()
        val type = if(isFood) AppConstants.FOOD else AppConstants.DRINK
        val description = binding.edtDescription.text.toString()

        if (name.isEmpty() || priceText.isEmpty() || type.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, getString(R.string.enter_full_info), Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toLongOrNull() ?: 0
        if (item == null) {
            if (isFood){
                viewModel.addFoodItem( name, price, type, description)
            }else{
                viewModel.addDrinkItem( name, price, type, description)
            }
        } else {
            val newItem = item!!.copy(name = name, price = price, isFavorite = item!!.isFavorite ,type = item!!.type, description = description)
            if (isFood){
                viewModel.updateFoodItem(newItem)
            }else{
                viewModel.updateDrinkItem(newItem)
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