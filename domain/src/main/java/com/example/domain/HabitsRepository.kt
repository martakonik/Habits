package com.example.domain

import kotlinx.coroutines.flow.Flow

interface HabitsRepository {

    suspend fun saveHabit(item: HabitItem)
    fun observeHabitsList(): Flow<List<HabitItem>>
}
