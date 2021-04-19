package com.example.ui.addhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.data.NewHabit
import com.example.ui.util.NavigationType
import com.example.ui.databinding.FragmentAddHabitBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddHabitFragment : Fragment() {
    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddHabitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.newHabit = NewHabit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationFlow.collect {
                it.consumeIfExists()?.let { navigationType ->
                    when (navigationType) {
                        is NavigationType.NavigateToDirection -> findNavController().navigate(
                            navigationType.direction
                        )
                        NavigationType.PopBackStack -> findNavController().popBackStack()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}