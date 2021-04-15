package com.example.ui.habitlist

import androidx.lifecycle.ViewModel
import com.example.domain.GetUserHabits
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val getUserHabits: GetUserHabits
) : ViewModel() {

    fun getListFromDatabase() = getUserHabits.get()

    fun addNewHabit() {

        HabitsListFragmentDirections.actionHabitListFragmentToAddHabitFragment()
    }
}
