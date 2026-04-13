package com.example.exerciseapplication.ui.drink

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import com.example.exerciseapplication.R
import com.example.exerciseapplication.databinding.FragmentItemBinding
import com.example.exerciseapplication.utils.setBorderColor

class DrinkFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    private val listNameDrink = listOf(
        R.string.orangeJuice,
        R.string.strawberryJuice,
        R.string.lemonTea,
        R.string.peachTea,
        R.string.coffee,
        R.string.blackCoffee,
        R.string.milkCoffee,
        R.string.coconutWater,
        R.string.watermelonJuice,
        R.string.pineappleJuice,
        R.string.mangoSmoothie,
        R.string.avocadoSmoothie,
        R.string.softDrink,
        R.string.mineralWater
    )

    private var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDrink.setBorderColor(R.color.lightGreen, R.color.greenMain)
        binding.tvDrink.setText(R.string.listDrink)

        binding.tvTitle.setBorderColor(borderSize = 8, borderColor = R.color.greenMain, backgroundColor = R.color.white)
        currentIndex = (0 until listNameDrink.size).random()
        updateUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun nextItem(){
        currentIndex = (currentIndex + 1) % listNameDrink.size
        updateUI()
    }

    fun previousItem(){
        currentIndex = if(currentIndex - 1 < 0) listNameDrink.size-1 else currentIndex -1
        updateUI()
    }

    private fun updateUI() {
        binding.tvTitle.setText(listNameDrink[currentIndex])
    }


}