package com.example.exerciseapplication.ui.home.fragment

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.model.MenuItem
import com.example.exerciseapplication.ui.home.adapter.RecycleViewAdapter
import com.example.exerciseapplication.ui.home.viewmodel.HomeViewModel
import com.example.exerciseapplication.utils.setBorderColor

class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private var isFood: Boolean = true

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

        val adapter = RecycleViewAdapter()
        binding.rvItem.adapter = adapter
        binding.rvItem.layoutManager = LinearLayoutManager(context)

        if (isFood) {
            binding.tvHeader.setBorderColor(R.color.lightRed, R.color.redBorder, 6)
            binding.tvHeader.text = getString(R.string.listFood)

            viewModel.food.observe(viewLifecycleOwner) { list ->
                list?.let {
                    adapter.setData(it)
                }
            }

        } else {
            binding.tvHeader.setBorderColor(R.color.lightGreen, R.color.greenMain)
            binding.tvHeader.text = getString(R.string.listDrink)

            // observe: lắng nghe sự thay đổi của dữ liệu
            viewModel.drink.observe(viewLifecycleOwner) { list ->
                list?.let {
                    adapter.setData(it)
                }
            }
        }

        adapter.onDeleteItem = { item: MenuItem ->
            viewModel.deleteItem(isFood, item)
        }

        adapter.onUpdateItem = { item: MenuItem ->
            openAddDialog(item)
        }
    }

    fun openAddDialog(item: MenuItem? = null) {
        if(item == null) {
            showAddDialog { name, price ->
                viewModel.addItem(isFood, name, price)
            }
        }else{
            showAddDialog(item) { name, price ->
                val newItem = MenuItem(item.id, name, price)
                viewModel.updateItem(isFood, newItem)
            }
        }
    }

    private fun showAddDialog(item: MenuItem? = null, onAdd: (String, Int) -> Unit) {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_add, null)

        val edtName = dialogView.findViewById<EditText>(R.id.edtName)
        val edtPrice = dialogView.findViewById<EditText>(R.id.edtPrice)

        item?.let {
            edtName.setText(it.name)
            edtPrice.setText(it.price.toString())
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Thêm món")
            .setView(dialogView)
            .setPositiveButton(if(item == null) "Thêm" else "Cập nhật", null)
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val name = edtName.text.toString().trim()
            val priceText = edtPrice.text.toString().trim()

            if (name.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toIntOrNull()
            if (price == null) {
                Toast.makeText(context, "Giá không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            onAdd(name, price)
            dialog.dismiss()
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