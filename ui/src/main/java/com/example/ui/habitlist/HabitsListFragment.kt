package com.example.ui.habitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ui.databinding.FragmentHabitsListBinding
import com.example.ui.habitItemView
import com.example.ui.habitListHeader
import com.example.ui.util.NavigationType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HabitsListFragment : Fragment() {

    private var _binding: FragmentHabitsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitsListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.insert(HabitItem(1, "nowy bazowy"))
            launch {
                viewModel.getListFromDatabaseWithState().collect { list ->
                    binding.habitList.withModels {
                        habitListHeader {
                            id(id)
                        }

                        list.forEach {
                            val viewModelPerItem: HabitListItemViewModel by viewModels()
                            habitItemView {
                                id("id")
                                habitWithState(it)
                                viewModelItem(viewModelPerItem)
                                navigate(object : Navigate {
                                    override fun navigateWithId(habitId: Long) {
                                        viewModel.onHabitItemClick(habitId)
                                    }
                                })
                            }
                        }
                    }
                }
            }
            launch {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}