package com.example.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserHabits @Inject constructor(
    private val repository: HabitsRepository
) {

    fun get(): Flow<List<HabitItem>> {
        return repository.observeHabitsList()
    }
}
