package com.example.domain

import com.example.domain.data.HabitWithStatePerDay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateHabitState @Inject constructor(
    private val repository: HabitsRepository
) {

    fun get(): Flow<List<HabitWithStatePerDay>> {
        return repository.observeHabitsList()
    }
}