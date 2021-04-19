package com.example.ui.habitlist

import com.example.ui.BaseViewModel
import com.example.ui.util.NavigationType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val model: HabitListModel
) : BaseViewModel() {

    fun getListFromDatabaseWithState() = model.getUserHabits()

    fun addNewHabit() {
        navigateTo(
            NavigationType.NavigateToDirection(
                HabitsListFragmentDirections.actionHabitListFragmentToAddHabitFragment()
            )
        )
    }

    fun onHabitItemClick(habitId: Long) {
        navigateTo(
            NavigationType.NavigateToDirection(
                HabitsListFragmentDirections.actionHabitListFragmentToHabitDetailsFragment(habitId)
            )
        )
    }
}
