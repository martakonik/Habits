package com.example.ui.habitdetails

import com.example.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    private val model: HabitDetailsModel
) : BaseViewModel() {
    fun getHabitDetails(habitId: Long) = model.getHabitDetails(habitId)

}