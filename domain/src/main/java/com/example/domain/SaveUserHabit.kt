package com.example.domain

import com.example.domain.data.HabitItem
import javax.inject.Inject

class SaveUserHabit @Inject constructor(
    private val repository: HabitsRepository
) {
    suspend fun save(item: HabitItem) {
        repository.saveHabit(item)
    }
}
