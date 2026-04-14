package com.example.exerciseapplication.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.ui.home.viewmodel.HomeViewModel
import com.example.exerciseapplication.utils.setBorderColor

class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private var type: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        type = arguments?.getInt(ARG_TYPE) ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == 0) {
            binding.tvHeader.setBorderColor(R.color.lightRed, R.color.redBorder, 6)
            binding.tvTitle.setBorderColor(borderColor = R.color.lightRed, borderSize = 8)

            viewModel.food.observe(viewLifecycleOwner) {
                binding.tvHeader.text = getString(R.string.listFood)
                binding.tvTitle.text = getString(it.name)
            }

        } else {
            binding.tvHeader.setBorderColor(R.color.lightGreen, R.color.greenMain)
            binding.tvTitle.setBorderColor(
                borderSize = 8, borderColor = R.color.greenMain,
            )

            // observe: lắng nghe sự thay đổi của dữ liệu/ không observer dữ liệu chỉ lắng nghe 1 lần
            viewModel.drink.observe(viewLifecycleOwner) {
                binding.tvHeader.text = getString(R.string.listDrink)
                binding.tvTitle.text = getString(it.name)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TYPE = "isFood"

        fun newInstance(type: Int): ItemFragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

}