package com.example.domain

import com.example.domain.data.StatePerDay
import javax.inject.Inject

class UpdateHabitExecution @Inject constructor(
    private val repository: HabitsRepository
) {
    suspend fun update(statePerDay: StatePerDay) {
        repository.updateHabitExecution(statePerDay)
    }

    suspend fun insert(statePerDay: StatePerDay) {
        repository.insertHabitExecution(statePerDay)
    }
}