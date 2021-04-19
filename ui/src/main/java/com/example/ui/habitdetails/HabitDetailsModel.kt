package com.example.ui.habitdetails

import com.example.domain.GetUserHabits
import javax.inject.Inject

class HabitDetailsModel @Inject constructor(
    private val getUserHabits: GetUserHabits
) {
    fun getHabitDetails(habitId: Long) = getUserHabits.getHabit(habitId)
}