package com.example.domain

import com.example.domain.data.HabitItem
import com.example.domain.data.HabitWithStatePerDay
import com.example.domain.data.StatePerDay
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {

    suspend fun saveHabit(item: HabitItem)
    suspend fun updateHabitExecution(item: StatePerDay)
    suspend fun insertHabitExecution(item: StatePerDay)
    fun observeHabitsList(): Flow<List<HabitWithStatePerDay>>
}
