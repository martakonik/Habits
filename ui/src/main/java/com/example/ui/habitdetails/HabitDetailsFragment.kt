package com.example.ui.habitdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ui.databinding.FragmentHabitDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HabitDetailsFragment : Fragment() {
    private var _binding: FragmentHabitDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.getHabitDetails(habitId)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}