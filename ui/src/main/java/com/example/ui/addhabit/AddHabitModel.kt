package com.example.ui.addhabit

import com.example.domain.data.HabitItem
import com.example.domain.SaveUserHabit
import javax.inject.Inject

class AddHabitModel @Inject constructor(
    private val saveUserHabit: SaveUserHabit
) {
  suspend fun saveHabit(item: HabitItem) {
      saveUserHabit.save(item)
  }
}