package com.example.ui.addhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ui.databinding.FragmentAddHabitBinding
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}