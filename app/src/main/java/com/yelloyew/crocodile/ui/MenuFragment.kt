package com.yelloyew.crocodile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.android.yelloyew.crocodile.R
import com.yelloyew.crocodile.ViewModel
import com.android.yelloyew.crocodile.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var _binding: FragmentMenuBinding
    private val binding get() = _binding

    private val viewModel: ViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        val navController = NavHostFragment.findNavController(this)

        binding.popularWordsButton.setOnClickListener {
            it.setBackgroundResource(R.drawable.circle_angle_pressed)
            navController.navigate(R.id.gameFragment)
            viewModel.gameController = 1
        }
        binding.seriouslyGameButton.setOnClickListener {
            it.setBackgroundResource(R.drawable.circle_angle_pressed)
            navController.navigate(R.id.gameFragment)
            viewModel.gameController = 2
        }
        binding.adultButton.setOnClickListener {
            it.setBackgroundResource(R.drawable.circle_angle_pressed)
            navController.navigate(R.id.gameFragment)
            viewModel.gameController = 3
        }
        binding.hardcoreButton.setOnClickListener {
            it.setBackgroundResource(R.drawable.circle_angle_pressed)
            navController.navigate(R.id.gameFragment)
            viewModel.gameController = 4
        }
        return binding.root
    }

}