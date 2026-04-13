package com.example.exerciseapplication.ui.fastfood

import android.annotation.SuppressLint
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

class FoodFragment : Fragment() {
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    private val listNameFood = listOf(
        R.string.fish,
        R.string.noodleSoup,
        R.string.crab,
        R.string.friedRice,
        R.string.grilledChicken,
        R.string.beefSteak,
        R.string.seafoodPizza,
        R.string.hamburger,
        R.string.frenchFries,
        R.string.friedChicken,
        R.string.spaghetti,
        R.string.sushi,
        R.string.salad,
        R.string.hotPot,
        R.string.springRoll
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDrink.setBorderColor(R.color.lightRed,R.color.redBorder, 6)
        binding.tvDrink.setText(R.string.listFood)

        binding.tvTitle.setBorderColor(borderColor = R.color.lightRed, borderSize = 8)
        currentIndex = (0 until listNameFood.size).random()
        updateUI()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun nextItem(){
        currentIndex = (currentIndex + 1) % listNameFood.size
        updateUI()
    }

    fun previousItem(){
        currentIndex = if(currentIndex - 1 < 0) listNameFood.size-1 else currentIndex -1
        updateUI()
    }

    private fun updateUI() {
        binding.tvTitle.setText(listNameFood[currentIndex])
    }


}